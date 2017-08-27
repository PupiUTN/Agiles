let vm= new Vue({
    el:'#appVue',
    data:{
        autocomplete:null,
        placeID:null,
        placeLocation:null,
        placeName:null,
        location:'',
        fechaReservaDesde: '',
        fechaReservaHasta:'',

    },
    mounted(){
        this.initDate();
        this.initAutocomplete();
        //this.geolocate();

    },
    methods:{
        initDate() {
            $('#booking-date-from').dateDropper();
            $('#booking-date-to').dateDropper();
        },
        initAutocomplete() {
            //https://developers.google.com/maps/documentation/javascript/examples/places-autocomplete-addressform
            input = document.getElementById('location');
            var options = {
                types: ['(cities)'],
                componentRestrictions: {country: "ar"}
            };
            // Create the autocomplete object, restricting the search to geographical
            // location types.
            this.autocomplete = new google.maps.places.Autocomplete(input, options);
            this.autocomplete.addListener('place_changed', () => {
                input.placeholder='Ciudad';
                let place = this.autocomplete.getPlace();
                if (place.geometry) {

                    this.placeID = place.place_id;
                    this.placeLocation= place.geometry.location;
                    this.placeName= input.value;



                } else {
                    this.placeID = null;
                    this.placeLocation= null;
                    this.placeName= null;
                    this.location = '';
                    sweetAlert("Oops...", "No se encuentra la ciudad", "error");

                }

        })
        },
        geolocate() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var lat = position.coords.latitude;
                    var long = position.coords.longitude;
                    //https://developers.google.com/maps/documentation/geocoding/start
                    $.get('https://maps.googleapis.com/maps/api/geocode/json?latlng=' + lat + ',' + long + '&sensor=true', function (data) {
                        var cityStatCountry = data.results[1].formatted_address;
                        this.location = cityStatCountry;
                        var place = data.results[1];
                        autocomplete.set("place", place);
                    });


                });
            };
        },
        buscar() {
            if (this.placeID != null) {
                console.log(this.placeID);
                console.log(this.fechaReservaDesde);
                console.log(this.fechaReservaHasta);
                window.location="/views/cuidadores/lista-cuidadores.html?placeName="+this.placeName+
                "&placeID="+this.placeID+
                "&lat="+this.placeLocation.lat()+
                "&lng="+this.placeLocation.lng();
            } else {
                sweetAlert("Oops...", "Ingrese una ciudad válida", "error");
                //no se ejecuta en orden... ver mas adelante
                this.location='';

            }
        }
    },
});



