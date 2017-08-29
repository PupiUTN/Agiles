let vm = new Vue({
    el: '#appVue',
    data: {
        user: {},
        dogs: [],
        url: "/api/user/",
        message: ""
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        toggleLoader() {
            $('#spinner').toggle();
        },
        getUserInfo() {
            axios.get("/api/user/me")
                .then((sessionInfo) => {
                    this.isUserLoggedIn(sessionInfo);
                })
                .catch(error => {
                    console.log(error);
                    sweetAlert("Oops...", "Error, ver consola", "error");
                });
        },
        isUserLoggedIn(sessionInfo) {
            if(sessionInfo.status === 200) {
                this.user = sessionInfo.data.principal.user;
                this.getUserDogs();
            }
            else {
                console.log(sessionInfo.status + "|" + sessionInfo.statusText);
                sweetAlert("Oops...", "Necesitas estar logueado para acceder a este contenido", "error");
            }
        },
        getUserDogs() {
            axios.get(this.url + this.user.id + "/perros")
                .then((response) => {
                    this.dogs = response.data;
                    if(this.dogs.length === 0) {
                        this.message="Actualmente no tenés ningún perro registrado. Agrega el primero!";
                    }
                    this.toggleLoader();
                })
                .catch(error => {
                    console.log(error);
                    sweetAlert("Oops...", "Error, ver consola", "error");
                });
        }
    }
});

