let app = (() => {
    const infopeli = document.getElementById("pelicula");

    var consultMovie = function () {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: "http://localhost:35000/?name="+document.getElementById("nombre-pelicula"),
                type: 'GET',
                succes: function(data){
                    resolve(data);
                },
                error: function(error){
                    reject("Error al hacer la peticion");
                }
            });
        })
    }; 

    var extractInfo = function () {
        consultMovie().then()
    }

    return {
        consultMovie
    }
})();