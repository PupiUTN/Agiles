Vue.component('my-detalle-reserva', {
    template: `
<div>
  <div id="titlebar">
        <div class="row">
            <div class="col-md-12">
                <h2>Datos de la Reserva </h2>
                <!-- Breadcrumbs -->
                <nav id="breadcrumbs">
                    <ul>
                       
                    </ul>
                </nav>
            </div>
        </div>
    </div>
      <div class="add-listing-section">

        <div class="add-listing-headline">
            <h3><i class="sl sl-icon-user-following" style="color:red;"></i> Información Básica de la Mascota</h3>
        </div>

        <div class="row with-forms">
            <div class="col-md-8">
                <h5>  </h5>
                <img id="foto_user"  :src="reserva.perro.fotoPerfil" alt="" style="width:300px;" >
            </div>
            <div class="col-md-4">
            <div class="col-md-12">
                <h5><i class="sl sl-icon-user-following" style="color:red; margin-right: 10px; "></i>  Nombre </h5>
                <h3><b>{{reserva.perro.nombre}}</b></h3>
            </div>

            <div class="col-md-12">
                <h5><i class="im im-icon-Old-Telephone" style="color:red; margin-right: 10px;"></i>Raza </h5>
                <h3><b>{{reserva.perro.raza.nombre}}</b></h3>
            </div>

           <div class="col-md-12">
                <h5><i class="im im-icon-Starfish" style="color:red; margin-right: 10px"></i>Calificacion </h5>
                <h3><b>  <div class="star-rating"  id="rating" data-rating ="0">
                  <span v-bind:class="{'star': puntaje >= 1, 'star empty': puntaje < 1 }" ></span>
                   <span v-bind:class="{'star': puntaje >= 2, 'star empty': puntaje < 2 }"></span>
                  <span v-bind:class="{'star': puntaje >= 3, 'star empty': puntaje < 3 }" ></span>
                <span v-bind:class="{'star': puntaje >= 4, 'star empty': puntaje < 4 }"></span>
                 <span v-bind:class="{'star': puntaje >= 5, 'star empty': puntaje < 5 }"></span>
               </div></b></h3>
            </div>
            <div class=""></div>
            <div class="col-md-12">
                <h5><i class="im im-icon-Paw" style="color:red; margin-right: 10px;" ></i>Sexo </h5>
                <h3><b>{{reserva.perro.sexo}}</b></h3>
            </div>
        </div>
          </div>


    </div>
    
      <!-- Section -->
    <div class="add-listing-section margin-top-45">

        <!-- Headline -->
        <div class="add-listing-headline">
            <h3><i class="" style="color:red; margin-right: 10px;" ></i> </h3>
        </div>

            <div class="row with-forms">
            <div class="col-md-8">
                <h5> <i class="im im-icon-Birthday-Cake" style="color:red; margin-right: 10px;"></i>Edad</h5>
                <h3><b>{{edadPerro}}</b></h3>
            </div>
            
             <div class="col-md-4">
                <h5><i class="im im-icon-Email" style="color:red; margin-right: 10px;"></i>Tamaño</h5>
                <h3><b>{{tamaño}}</b></h3>
            </div>
            
            
        </div>
        
         <div class="row with-forms">
            <div class="col-md-12">
                <h5> <i class="im im-icon-Heart" style="color:red; margin-right: 10px;"></i>Vacunas</h5>
               <div class="col-md-12" style="padding-left: 0px;">
                        <!--<label class="col-md-1"></label>-->
                        <ul v-for=" vacuna in reserva.perro.listaVacunas" class=" col-md-3 listing-features checkboxes margin-top-0"
                            style=" padding-left: 0px;">
                            <li> {{vacuna.nombre}}</li>
                        </ul>
                    </div>
            </div>            
          </div>
    </div>
    
    
     <div class="add-listing-section margin-top-45">
       <div class="add-listing-headline">
            <h3><i class="sl sl-icon-user-following" style="color:red;"></i> Información Básica de la Reserva</h3>
        </div>
     
       <div class="row with-forms">
            <div class="col-md-8">
                <h5><i class="sl sl-icon-user-following" style="color:red; margin-right: 10px;"></i>Usuario</h5>
                <h3><b>{{reserva.perro.user.fullName}}</b></h3>
            </div>

            
            <div class="col-md-4">
                <h5><i class="im im-icon-Timer-2" style="color:red; margin-right: 10px; "></i>Fecha De Reserva </h5>
                <h3><b>{{reserva.fechaTransaccion}}</b></h3>
            </div>
        </div>
        
        <div class="row with-forms">
            <div class="col-md-12">
                <h5><i class="im im-icon-Mailbox-Full" style="color:red; margin-right: 10px;"></i>Mensaje</h5>
                <h3><b>{{reserva.mensaje}}</b></h3>
            </div>

        </div>

     </div>
    
       <div class="add-listing-section margin-top-45">

        <div class="add-listing-headline">
            <h3><i class="" style="color:red;"></i> </h3>
        </div>
        
        <!-- Title -->
        <div class="row with-forms">
           <div class="col-md-8">
                <h5><i class="im im-icon-Money-2" style="color:red; margin-right: 10px; "></i>Mi ganancia</h5>
                <h3><b><label style=" color: green;font-size: 30px;">&#36 {{ganancia}} </label></b></h3>
            </div>

            <div class="col-md-4">
                <h5><i class="im im-icon-Money-2" style="color:red; margin-right: 10px; "></i>Precio total</h5>
                <h3><b><label style=" color: green;font-size: 30px;">&#36 {{reserva.precioTotal}} </label></b></h3>
            </div>
        </div>

        <!-- Row -->
        <div class="row with-forms">

            <!-- Status -->
            <div class="col-md-8">
                <h5><i class="im im-icon-Timer-2" style="color:red; margin-right: 10px;"></i>Fecha De entrada</h5>
                <h3><b>{{reserva.fechaInicio}}</b></h3>
            </div>

            <!-- Type -->
            <div class="col-md-4">
                <h5><i class="im im-icon-Timer-2" style="color:red; margin-right: 10px;"></i>Fecha De Salida </h5>
                <h3><b>{{reserva.fechaFin}}</b></h3>
            </div>

        </div>

    </div>
    
    
    <div v-if="reserva.status === 'pagada-dueño' || reserva.status === 'ejecucion' ">

      <div class="add-listing-section margin-top-45">

        <div class="add-listing-headline">
            <h3><i class="sl sl-icon-user-following" style="color:red;"></i> Información del Dueño </h3>
        </div>
         <!-- Title -->
        <div class="row with-forms">
            <div class="col-md-8">
                <h5><i class="im im-icon-Timer-2" style="color:red; margin-right: 10px;"></i>Número De reserva</h5>
                <h3><b>{{reserva.id}}</b></h3>
            </div>

            <div class="col-md-4">
                <h5><i class="im im-icon-Email" style="color:red; margin-right: 10px; "></i>Email </h5>
                <h3><b>{{reserva.perro.user.email}}</b></h3>
            </div>
        </div>

        <!-- Row -->
        <div class="row with-forms">

            <!-- Status -->
            <div class="col-md-8">
                <h5><i class="im im-icon-Old-Telephone" style="color:red; margin-right: 10px;"></i>Teléfono</h5>
                <h3><b>{{reserva.perro.user.phone}}</b></h3>
            </div>

            <!-- Type -->
            <div class="col-md-4">
                <h5><i class="im im-icon-Birthday-Cake" style="color:red; margin-right: 10px;"></i>Edad </h5>
                <h3><b>{{edadUsuario}}</b></h3>
            </div>

        </div>
        
          <!-- Row -->
        <div class="row with-forms">

            <!-- Status -->
            <div class="col-md-8">
                <h5><i class="im im-icon-City-Hall" style="color:red; margin-right: 10px;"></i>Ciudad</h5>
                <h3><b>{{reserva.perro.user.direccion.ciudad}}</b></h3>
            </div>

            <!-- Type -->
            <div class="col-md-4">
                <h5><i class="im im-icon-Flag-2" style="color:red; margin-right: 10px;"></i>Provincia</h5>
                <h3><b>{{reserva.perro.user.direccion.provincia}}</b></h3>
            </div>

        </div>
 
        </div>
    </div>
    
    
          <div class="add-listing-section margin-top-45">

        <div class="add-listing-headline">
            <h3 style="height: 32px;"><i class="fa fa-comments-o  margin-top-20 margin-bottom-20" style="color:red;"></i> Reviews ({{DataReview.length}})  </h3>
        </div>

        <div class="row with-forms">
            <div class="col-md-12">
                     <!-- Reviews -->
         <section class="comments listing-reviews" >

                <ul style=" list-style: none;">
                    <li v-for="(calificacion, index) in calificaciones  ">
                        <div class="col-md-2"></div>
                        <div class="col-md-2 col-xs-12 avatar"><img :src="calificacion.reserva.cuidador.user.profileImageUrl" alt=""/>
                        </div>
                        
                        <div class="comment-content">

                            <div class="comment-by">{{calificacion.reserva.cuidador.user.fullName}}<span class="date"></span>
                                    <div class="col-md-3 star-rating" >
                                        <span v-bind:class="{'star': calificacion.puntaje >= 1, 'star empty': calificacion.puntaje < 1 }" ></span>
                                        <span v-bind:class="{'star': calificacion.puntaje >= 2, 'star empty': calificacion.puntaje < 2 }"></span>
                                        <span v-bind:class="{'star': calificacion.puntaje >= 3, 'star empty': calificacion.puntaje < 3 }" ></span>
                                        <span v-bind:class="{'star': calificacion.puntaje >= 4, 'star empty': calificacion.puntaje < 4 }"></span>
                                        <span v-bind:class="{'star': calificacion.puntaje >= 5, 'star empty': calificacion.puntaje < 5 }"></span>
                                    </div>
                                </div>
                            
                                <p> {{calificacion.comentario}}</p>

                            </div>
                        
                    </li>

                </ul>
            </section>

            <!-- Pagination -->
            <div class="clearfix"></div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Pagination -->
                    <div class="pagination-container margin-top-15">
                        <nav class="pagination">
                            <ul>
                                <ul>              
                    <li><a :style="offset > 0 ? 'background-color: crimson' : 'background-color: darkgrey'" @click="previous()"><i class="sl sl-icon-arrow-left" style=" font-weight: bold;color: white;"></i></a></li>

                    <li><a  :style="(offset + perPage) < DataReview.length ? 'background-color: crimson' : 'background-color: darkgrey'" @click="next()"><i class="sl sl-icon-arrow-right" style=" font-weight: bold;color: white;"></i></a></li>
                </ul>

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <!-- Pagination / End -->
            </div>
           
        </div>


    </div>
   
     <br>

    <div class="center-block">

        <div class="col-md-1"></div>
        <div class="col-xs-5 col-md-3" v-if="reserva.status === 'creada-dueño' && reserva.status !== 'ejecucion' " >
            <a v-on:click="confirmarReservaButton()"  style="background-color: inherit; color: blue; border-color: blue; " href="#" class="button medium border pull-right"><i class="sl sl-icon-docs"></i> Confirmar</a>
        
        </div>
            <div class="col-xs-5 col-md-6" v-if="reserva.status !== 'rechazada-cuidador' && reserva.status !== 'ejecucion' && reserva.status !== 'pagada-dueño'" >
            <a v-on:click="cancelarReservaActionButton()"  hstyle="background-color: inherit;" ref="#" class="button medium border pull-right"><i class="sl sl-icon-docs"></i> Cancelar</a>
        </div>
        
    </div>

</div>
    `,
    data: function () {
        return {
            url: '',
            reserva: {
                id: null,
                perro: {
                    user: {
                        fullName: '',
                        profileImageUrl: '',
                        email: '',
                        phone: '',
                        birthday: '',
                        direccion: {
                            calle: '',
                            ciudad: '',
                            numero: '',
                            latitud: '',
                            longitud: '',
                            provincia: ''

                        },

                    },
                    fotoPerfil: '',
                    nombre: '',
                    birthday: '',
                    raza: {},
                    sexo: '',
                    tamaño: {},
                    listaVacunas: [],
                    id: ''
                },
                fechaInicio: "",
                fechaFin: "",
                precioTotal: 1,
                status: '',
                mensaje: '',
                transaccion: '',

            }
            ,
            message: '',
            perroProfileUrl: '',
            id: null,
            edadPerro: '',
            showModal: false,
            tamaño: '',
            edadUsuario: '',
            numeroReserva: '',
            puntaje: 0,
            calificaciones: [{
                id: '',
                comentario: '',
                puntaje: '',
                from_owner: '',
                reserva: {
                    id: '',
                    status: '',
                    cuidador: {
                        user: {
                            profileImageUrl: '',
                            username: '',
                            fullName:''
                        }
                    },
                    fechaTransaccion: '',
                },
            }],
            offset: 0,
            navButtons: [],
            perPage: 2,
            DataReview: [],
            puntajeUsuario: 0,
            ganancia: '',

        }
    },
    mounted() {
        this.id = this.getParameterByName('id');
        this.getReserva();
    },
    methods: {

        getReserva() {
            axios.get('/api/cuidador/me/reservas/' + this.id)
                .then((response) => {
                    this.reserva = response.data;
                    // document.getElementById("foto_perro").src = this.reserva.perro.fotoPerfil;
                    if (this.reserva.perro.birthday !== null) {
                        this.edadPerro = this.calcularEdad(this.reserva.perro.birthday);
                    }
                    else {
                        this.edadPerro = "-";
                    }
                    var date = new Date(this.reserva.fechaTransaccion);
                    this.reserva.fechaTransaccion = date.toLocaleDateString('en-GB');
                    if (this.reserva.cuidador.user.birthday !== null) {
                        this.edadUsuario = this.calcularEdad(this.reserva.cuidador.user.birthday);
                    }
                    else {
                        this.edadUsuario = "";

                    }
                    this.numeroReserva = 570011223344;
                    if (this.edadPerro === 0) {

                        this.edadPerro = " menor a un año";
                    } else {
                        if (this.edadPerro === 1) {
                            this.edadPerro = this.edadPerro + " " + "año";

                        } else if (this.edadPerro > 1) {

                            this.edadPerro = this.edadPerro + " " + "años";
                        } else {
                            this.edadPerro = " ";
                        }

                    }
                    this.ganancia = (this.reserva.precioTotal - (this.reserva.precioTotal * 0.2533)).toFixed(2)
                    this.tamaño = this.reserva.perro.tamaño.nombre + " " + " (" + this.reserva.perro.tamaño.valorMinimo + " - " + this.reserva.perro.tamaño.valorMaximo + ")" + " kgs";
                    //  this.reserva.mensaje = " hola que tal buenos dias cmo va
                    this.getCalificacionesPerro();
                })
                .catch(error => {
                    console.log(error);
                    this.message = "Actualmente no se encuentra la reserva.";
                    sweetAlert("Oops...", "Actualmente no se encuentra la reserva.", "error");
                });
        },
        cancelarReserva() {

            var id = this.reserva.id;
            axios.put('/api/cuidador/me/reservas/' + id + '/cancelarReserva')
                .then((response) => {

                    sweetAlert("Cancelada", "Tu reserva ha sido cancelada", "success");
                    document.location.href = "/views/dashboard/dashboard.html";
                })
                .catch(error => {
                        console.log(error);
                        sweetAlert("Oops...", "Error, ver consola", "error");
                    }
                );
        },
        ConfirmarReserva() {

            var id = this.reserva.id;
            axios.put('/api/cuidador/me/reservas/' + id + '/confirmarReserva')
                .then((response) => {

                    sweetAlert({
                            title: "Aceptada",
                            text: "Has confirmado la solicitud de reserva, cuando el dueño pague te confirmaremos la reserva.",
                            type: "success",
                        },
                        function () {
                            document.location.href = "/views/reserva/mis-reservas-cuidador.html?status=aceptada-cuidador";
                        })
                })
                .catch(error => {
                        console.log(error);
                        sweetAlert("Oops...", "Error, ver consola", "error");
                    sweetAlert({
                            title: "Error",
                            text: "Error, ver consola",
                            type: "success",
                        },
                        function () {
                            document.location.href = "/views/dashboard/dashboard.html";

                    }
                );
        })
        },
        confirmarReservaButton() {

            sweetAlert({
                    title: "Confirmar accion",
                    text: "Quiere confirmar su reserva con " + this.reserva.perro.user.fullName + " ?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Si, confirmar reserva",
                    closeOnConfirm: false,
                    cancelButtonText: "Atras",
                    showLoaderOnConfirm: true,
                },
                function () {
                    vm.$refs.myDetalleReserva.$refs.currentView.ConfirmarReserva();

                });
        },
        cancelarReservaActionButton() {

            sweetAlert({
                    title: "Confirmar accion",
                    text: "Quieres rechazar su reserva con " + this.reserva.perro.user.fullName + " ?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Si, cancelar reserva",
                    closeOnConfirm: false,
                    cancelButtonText: "Atras",
                    showLoaderOnConfirm: true,
                },
                function () {

                    vm.$refs.myDetalleReserva.$refs.currentView.cancelarReserva();

                });
        },
        getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        },
        calcularEdad(fecha) {
            // var fecha=document.getElementById("user_date").value;

            // Si la fecha es correcta, calculamos la edad
            var values = fecha.split("-");
            var dia = values[2];
            var mes = values[1];
            var ano = values[0];

            // cogemos los valores actuales
            var fecha_hoy = new Date();
            var ahora_ano = fecha_hoy.getYear();
            var ahora_mes = fecha_hoy.getMonth() + 1;
            var ahora_dia = fecha_hoy.getDate();

            // realizamos el calculo
            var edad = (ahora_ano + 1900) - parseInt(ano);
            if (ahora_mes < mes) {
                edad--;
            }
            if ((mes === ahora_mes) && (ahora_dia < dia)) {
                edad--;
            }
            if (edad > 1900) {
                edad -= 1900;
            }
            return edad;

        },
        getCalificacionesPerro()
        {
            var urlCalificaciones = "/api/calificaciones/calificacionesPerro/";

            axios.get(urlCalificaciones + '?id=' + this.reserva.perro.id)
                .then((response) => {
                    this.DataReview = response.data;
                    var cont = 0;
                    this.DataReview.forEach(function (item, value, array) {


                        cont += item.puntaje;

                    });
                    if (this.DataReview.length > 0) {
                        this.puntaje = Math.trunc(cont / this.DataReview.length);

                        var h = document.getElementsByClassName("star empty");
                        for (i = 0; i < (this.puntaje - 1); i++) {
                            h[0].className = 'star';

                        }

                    }
                    this.paginate();
                })
                .catch(error => {
                        console.log(error);
                        sweetAlert("Oops...", "Error  ", "error");

                    }
                );


        },
        paginate() {
            this.calificaciones = this.DataReview.slice(this.offset, this.offset + this.perPage);

        },
        previous() {
            if(this.offset >0)
            this.offset = this.offset - this.perPage;
        },
        next () {
            if (this.offset + this.perPage < this.DataReview.length)
            this.offset = this.offset + this.perPage;
        },
    },
    watch: {
        offset: function () {
            this.paginate();
        },
    }


});
