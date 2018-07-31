Vue.component('become-cuidador', {
    template: `
<div>
 <div id="titlebar">
    <div class="row">
        <div class="col-md-12">
            <h2>Solicitud de Cuidador</h2>
        </div>
    </div>
</div>
    <div class="row">
    <div class="col-md-12" >
        <div class="notification warning" v-show="isReadOnly">
            <p><span> Atencion!</span> Su solicitud esta en proceso.</p>
        </div>
        <div class="dashboard-list-box margin-top-0">
            <h4 class="gray"> 	<i class="fa fa-paw"></i> Legal </h4>
            <div class="dashboard-list-box-static"  > 
            <ul>
                <li><i class="fa fa-paw"></i>
                Recuerda que PUPI solo aprueba a aquellas personas que tienen su perfil completo. Por favor, verifica
                que tus datos estén cargados y sean correctos 
                <a href="/views/dueño/perfil.html"><span style="color:blue"> aquí</span></a>.
                </li>
                <li><i class="fa fa-paw"></i>
                Agrega una descripción
                sobre tí para que te conozcamos más. Luego será usada para que los demás puedan conocerte mejor.
                Recomendamos que incluyas tu experiencia con perros, tu disponibilidad para ellos y que nos cuentes
                todo ese amor que le tienes!
                </li>
                <li><i class="fa fa-paw"></i>
                Además, PUPI requiere el DNI o cédula y foto (de ambos lados) para validar de que eres una persona de confianza.
                Queremos asegurarnos de que nuestros perros sean cuidados por personas que los aman. 
                </li>
                <li><i class="fa fa-paw"></i>
                PUPI puede aceptar o rechazar tu solicitud. En ambos casos, te llegará un mail explicando más acerca 
                de la decisión.
                </li>
                </ul>
            </div>
        </div>
    </div>
    </div>
    <form id="altaCuidadorForm" v-on:submit.prevent='enviarAltaCuidador()' enctype="multipart/form-data">
    <div class="row">
    <div class="col-md-12 margin-top-20 margin-bottom-20" >
        <div class="dashboard-list-box margin-top-0">
            <h4 class="gray"> 	<i class="fa fa-paw"></i> Vincular Cuenta de MercadoPago </h4>
            <div class="dashboard-list-box-static"  > 
                        <label class="margin-top-0">Necesitamos que vincules tu cuenta de MercadoPago a tu cuenta de Pupi, para que podamos
            realizar el cobro de la estadía, y automáticamente el dinero sea depositado en tu cuenta.</label>
            <div v-if="hasMpToken == false">
                <a v-on:click="getMpToken" class="button preview pull-right" >Vincular <i class="fa fa-arrow-circle-right"></i></a>
            </div>
            <div v-else align="right">
                Validada <i class="im im-icon-Security-Check"></i>
            </div>
            </div>
        </div>
    </div>
    </div>
    <div class="col-lg-6 col-md-12 margin-top-20 margin-bottom-20 " style="height:80%">
        <div class="dashboard-list-box margin-top-0">
            <h4 class="gray"> 	<i class="fa fa-paw"></i> Descripción </h4>
        </div>
            <div class="dashboard-list-box-static" style="height:100%" >   
                <label class="margin-top-0">Por favor, realiza una descripción sobre tí. Recuerda
                que debe ser lo más clara posible, así PUPI y otros usuarios puedan saber más acerca de tí.</label>
                 <textarea v-model="cuidador.descripcion" v-bind:readonly="isReadOnly" rows="6" cols="50" maxlength="150"  placeholder="Descripcion"
                    style="height: " required>
                 </textarea>
            </div>
    </div>
    <div class="col-lg-6 col-md-12 margin-top-20 margin-bottom-20" style="height:80%" >
        <div class="dashboard-list-box margin-top-0">
            <h4 class="gray"> 	<i class="fa fa-paw"></i> DNI o Cédula  </h4>
        </div>
        <div class="dashboard-list-box-static" style="height:100%">
            <div class="row" >
                <div class="col-lg-6 col-md-12">
                    <label class="margin-top-0">Anverso</label>
                    <div class="edit-profile-photo">
                        <img :src="dniAnverso.url" alt="DNI" height="170">
                        <div class="change-photo-btn" id="dniReverso">
                            <div class="photoUpload">
                                <span><i class="fa fa-upload"></i> Subir Foto</span>
                                <input type="file"  :disabled="isReadOnly"  @change="filesChange($event.target.files,0)" class="upload" accept="image/x-png,image/jpg,image/jpeg"/>
                            </div>
                        </div>
                    </div>
                </div>  
                <div class="col-lg-6 col-md-12">
                    <label class="margin-top-0">Reverso</label>
                    <div class="edit-profile-photo">
                        <img :src="dniReverso.url" alt="DNI" height="170">
                        <div class="change-photo-btn" id="dniAnverso">
                            <div class="photoUpload">
                                <span><i class="fa fa-upload"></i> Subir Foto</span>
                                <input type="file" :disabled="isReadOnly"  @change="filesChange($event.target.files, 1)" class="upload" accept="image/x-png,image/jpg,image/jpeg"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <label>Número</label>
                <input v-model="cuidador.dni"  :disabled="isReadOnly" value="" type="number" max="99999999" required>
            </div>
        </div>
    </div>
    <div class="row">
    <div class="col-s-3" style="margin-left: 2%;">
    <input type="submit" :disabled="isReadOnly" value="Enviar" name="enviarAltaCuidador" style=" height: 60px; width: 150px; position: relative; " class="button margin-top-10"/>
    </div>
    </div>
    </form>
    </div> 
              
</div>
    `,
    data: function () {
        return {
            isReadOnly: false,

            url: "/api/user/",
            dniAnverso: {
                url: "/assets/images/nodni.png"
            },
            dniReverso: {
                url: "/assets/images/nodni.png"
            },
            cuidador: {
                estado: "pending",
            },
            user: {},
            hasMpToken: false
        }

    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        filesChange(fileList, position) {
            // handle file changes
            const formData = new FormData();

            if (!fileList.length) return;
            // append the files to FormData
            Array
                .from(Array(fileList.length).keys())
                .map(x => {
                    formData.append('file', fileList[x]);
                });

            // save it
            this.upload(formData, position);
        },
        upload(formData, position) {
            if (!this.isReadOnly) {

                axios.post('/api/file/', formData)
                    .then((response) => {

                        var url = response.data;
                        console.log(url);
                        if (position == 0) {
                            this.dniAnverso.url = url;

                        } else {
                            this.dniReverso.url = url;
                        }
                    })
                    .catch(error => {
                            console.log("ERROR AXIOS");
                            console.log(error);
                        }
                    );
            }
        },
        getUserInfo() {
            axios.get(this.url + "me")
                .then((sessionInfo) => {
                    this.isUserCuidador(sessionInfo);
                })
                .catch(error => {
                    //console.log(error);
                    document.location.href = "/";
                });
        },

        isUserCuidador(sessionInfo) {
            this.user = sessionInfo.data.principal.user;
            console.log(this.user);
            if (this.user.role == 'ROLE_CUIDADOR') {
                sweetAlert({
                        title: "Felicidades!!",
                        text: "Su solicitud ha sido aprobada. Ahora puede armar su perfil como cuidador",
                        type: "success",
                    },
                    function () {
                        document.location.href = "/views/cuidadores/cuidadores-editar.html";
                    });
            }
            this.solicitudExistente();
            this.checkIfHasMpToken();

        },
        solicitudExistente() {
            var url = "/api/cuidadores/user/";
            let consulta = url + '?id=' + this.user.id;

            axios.get(consulta)
                .then((data) => {
                    if (data.data != "") {
                        this.cuidador = data.data;

                        this.inicializarImagenes();
                        if (this.cuidador.estado == "rejected") {

                            sweetAlert({
                                title: "Atención!",
                                text: "Su solicitud ha sido rechazada. Por favor, revise su solicitud y envie una nueva.",
                                type: "error",
                            });
                            return;
                        } else {
                            this.isReadOnly = true;
                        }

                    }

                    // this.formPost = false;

                })
                .catch(error => {
                    //  this.formPost = true;
                    // document.getElementById("spinner").toggle();
                    // me redirije a lo de jorge
                    console.log(error);
                });

        },
        enviarAltaCuidador() {
            if (!this.isReadOnly) {

                if (this.hasMpToken === false) {
                    sweetAlert("Oops...", "Error, debes vincular tu cuenta de MercadoPago!", "error");
                    return;
                }

                if (this.validarCantidadImagenes()) {
                    sweetAlert("Oops...", "Error, Se deben cargar ambas imagenes del dni", "error");
                    return;
                }
                if (this.user.status == "INCOMPLETED") {
                    sweetAlert({
                            title: "Alerta!",
                            text: "Para enviar su solicitud, primero debe completar su perfil.",
                            type: "warning",
                            confirmButtonColor: '#f91942',
                            cancelButtonColor: '#707070',
                            confirmButtonText: 'Editar perfil',
                            showCancelButton: true,
                            cancelButtonText: 'Cancelar'
                        },
                        function () {
                            document.location.href = "/views/dueño/perfil.html";
                        });
                    return;
                }
                this.cuidador.dniImagenes = [this.dniAnverso, this.dniReverso];
                this.cuidador.user = this.user;
                console.log(this.cuidador);
                var urlCuidador = "/api/cuidadores/";
                axios.post(urlCuidador, this.cuidador)
                    .then((response) => {
                        console.log(response);
                        swal({
                                title: "Enviada!",
                                text: "Solicitud generada exitosamente",
                                type: "success"
                            },
                            function () {
                                window.location.href = "/views/dashboard/dashboard.html";
                            });
                    })
                    .catch(error => {
                            console.log(error);
                            sweetAlert("Oops...", "Error, ver consola lalalal", "error");
                        }
                    );
            }
        },
        inicializarImagenes() {
            this.dniAnverso.url = this.cuidador.dniImagenes[0].url;
            this.dniReverso.url = this.cuidador.dniImagenes[1].url;
        },
        validarCantidadImagenes() {
            return this.dniAnverso.url == "/assets/images/nodni.png" || this.dniReverso.url == "/assets/images/nodni.png";
        }
        ,

        getMpToken() {
            axios.get(this.url + "/get-mp-url?email=" + this.user.email)
                .then((tokenUrl) => {
                    window.location.href = tokenUrl.data
                })
        },
        checkIfHasMpToken() {
            axios.get(this.url + "/has-mp-token?email=" + this.user.email)
                .then((hasMpToken) => {
                    console.log(hasMpToken);
                    this.hasMpToken = hasMpToken.data;
                })
        }
    }
});

