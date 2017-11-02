// https://github.com/olefirenko/vue-google-autocomplete
var myBuscarCuidadores = Vue.component('my-buscar-cuidadores', {
    template: `
<form class="main-search-input" v-on:submit.prevent='buscar'>
    <div class="main-search-input-item location" :style="inputSize">
        <vue-google-autocomplete
            id="location"
            placeholder="Ciudad"
            country="ar"
            types="(cities)"
            v-on:placechanged="setPlaceId"
        >
        </vue-google-autocomplete>
        <a id="geo-location" v-on:click='geolocate()' v-show="isIndex">
            <i class="fa fa-dot-circle-o"></i>
        </a>
        

    </div>
    <div class="main-search-input-item location">
        <my-hotel-date-picker v-model="dateFrom"></my-hotel-date-picker>
              <a><i class="fa fa-calendar-check-o"></i></a>
    </div>
    <button class="button" type="submit">
        Buscar
    </button>
    

</form>

    `,
    props: {
        isIndex: {
            type: Boolean,
            default: true
        }
    },
    data:
        function () {
            return {
                placeID: null,
                placeLat: null,
                placeLng: null,
                placeName: null,
                dateFrom: null,
                dateTo: null,
            }
        },
    mounted() {
        this.bindUrlWithVue();
        this.bindDatePickerWithVue();

    },
    methods: {
        geolocate() {
            if (navigator.geolocation) {
                $('#page-loader').show();
                navigator.geolocation.getCurrentPosition(function (position) {
                    console.log("entra a get current position");
                    let lat = position.coords.latitude;
                    //trunca el valor a 3 decimales
                    this.placeLat = lat.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0];
                    let long = position.coords.longitude;
                    this.placeLng = long.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0];
                    //https://developers.google.com/maps/documentation/geocoding/start
                    axios.get('https://maps.googleapis.com/maps/api/geocode/json?latlng=' + this.placeLat + ',' + this.placeLng + '&sensor=true')
                        .then((data) => {
                            console.log(data.data);
                            var city = data.data.results[1];
                            vm.$refs.myIndex.$refs.currentView.$refs.myBuscarCuidadores.placeID = city.place_id;
                            vm.$refs.myIndex.$refs.currentView.$refs.myBuscarCuidadores.placeLat = city.geometry.location.lat;
                            vm.$refs.myIndex.$refs.currentView.$refs.myBuscarCuidadores.placeLng = city.geometry.location.lng;
                            vm.$refs.myIndex.$refs.currentView.$refs.myBuscarCuidadores.placeName = city.formatted_address;
                            let input = document.getElementById('location');
                            input.value = vm.$refs.myIndex.$refs.currentView.$refs.myBuscarCuidadores.placeName;

                        });
                });
            }
            ;
        },
        setPlaceId(addressData, placeResultData) {
            this.placeID = placeResultData.place_id;
            this.placeLat = addressData.latitude;
            this.placeLng = addressData.longitude;
            this.placeName = placeResultData.formatted_address;
        },
        setDateRange(dateRange) {
            console.log("setDateRange"
                + dateRange)

        },
        buscar() {
            //si el formulario no tiene los campos basicos no hago nada
            if (!this.formValitaion()) {
                console.log("place id not set in vue-search component");
                return;
            }
            let href = "/views/cuidadores/lista-cuidadores.html?placeName=" + this.placeName +
                "&placeID=" + this.placeID +
                "&lat=" + this.placeLat +
                "&lng=" + this.placeLng;
            //con el datapicker los datos no se "bindean" en el dom...
            this.dateFrom = document.getElementById("dateFrom").value;
            this.dateTo = document.getElementById("dateTo").value;
            console.log(this.dateFrom);
            console.log(this.dateTo);
            if (this.dateFrom != '') {
                if (this.dateTo != '') {
                    if (this.dateTo >= this.dateFrom) {
                        href += "&from=" + this.dateFrom +
                            "&to=" + this.dateTo;
                    } else {
                        sweetAlert("Oops...", "La fecha hasta debe ser mayor a la desde", "error");
                        this.dateTo = '';
                        return;

                    }
                } else {
                    sweetAlert("Oops...", "Debe ingresar una fecha hasta", "error");
                    return;
                }
            }
            // router.push({ name: 'buscar'});

            window.location.href = href;

        },

        //obitene los parametros de la url... copiado de internet
        getParameterByName(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        },
        bindUrlWithVue() {
            this.dateFrom = this.getParameterByName('from');
            this.dateTo = this.getParameterByName('to');
            this.placeID = this.getParameterByName('placeID');
            this.placeName = this.getParameterByName('placeName');
            this.placeLat = this.getParameterByName('lat');
            this.placeLng = this.getParameterByName('lng');
            //
            if (this.placeName != undefined) {
                let input = document.getElementById('location');
                input.placeholder = this.placeName;
                input.value = this.placeName;
            }
        },
        formValitaion() {
            if (this.placeID == null) {
                return false;
            }
            return true;
        }
    },
    computed: {
        inputSize() {
            if (this.isIndex) {
                return 'flex: 1';
            }
            return 'flex: 2';
        }
    }
});
