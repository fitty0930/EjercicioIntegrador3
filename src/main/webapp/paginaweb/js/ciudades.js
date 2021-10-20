document.addEventListener("DOMContentLoaded", function() {
    function getCiudades() {
        let myHeaders = new Headers();
        let requestOptions = {
            method: 'GET',
            redirect: 'follow',
        };

        //ESTO SE AVANZA EN LA SEGUNDA PARTE 
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/ciudades", requestOptions)
            .then(response => response.json())
            .then(data => {
                let string = ""
                data.forEach(ciudad => {
                    string += `<li href="#" class="list-group-item text-left">
                        <div class="contentEstudiente">
                            <img class="img-thumbnail "
                                src="https://bootdey.com/img/Content/User_for_snippets.png">
                            <label class="name ms-2">
                                ${ciudad.pais} ${ciudad.provincia} ${ciudad.nombreCiudad}<br>
                            </label>
                        </div>
                        <div class="abmEstudient">
                            <span class="pull-right ">
                                <span><i class="fas fa-users mt-4"></i> 39</span>
                                <a class="btn-delete"  id="${ciudad.idCiudad}" type="button"><i class="fas fa-trash-alt color-danger ms-3 mt-4"></i></a>
                            </span>
                        </div>
                        <!-- <div class="break"></div> -->
                    </li>`;
                });
                document.querySelector(".ctn-ciudades").innerHTML = string;
                const btn = document.querySelectorAll(".btn-delete");
                for (let i = 0; i < btn.length; i++) {
                    btn[i].addEventListener("click", function() {
                        deleteCiudades(btn[i].id)
                    });
                }
            })
            .catch(error => console.error(error));
    }

    function deleteCiudades(id) {
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", 'application/json');
        myHeaders.append("Access-Control-Allow-Origin", "*");
        let requestOptions = {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow',
        };
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/ciudades/" + id, requestOptions)
            .then(res => {
                console.log(res);
                getCiudades();
            })
            .catch((error) => console.log(error))
    }

    function filter(idEstudiante) {
        //  console.log(idEstudiante)

        let myHeaders = new Headers();
        let requestOptions = {
            method: 'GET',
            redirect: 'follow',
        };

        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes" + "/ciudades/searchname/" + idEstudiante + "", requestOptions)
            .then(response => response.json())
            .then(ciudad => {

                let string = `<li href="#" class="list-group-item text-left">
                    <div class="contentEstudiente">
                        <img class="img-thumbnail " src="https://bootdey.com/img/Content/User_for_snippets.png">
                        <label class="name ms-2">
                        ${ciudad.pais} ${ciudad.provincia} ${ciudad.nombreCiudad}<br>
                      </label>
                  </div>
                  <div class="abmEstudient">
                      <span class="pull-right">
                          <i class="far fa-eye mt-4"></i>
                          <a class="btn-delete" id="${ciudad.idCiudad}" type="button"><i class="fas fa-trash-alt color-danger ms-3"></i></a>
                          <i class="fas fa-envelope-square ms-3"></i>
                      </span>
                  </div>
                  <!-- <div class="break"></div> -->
                </li>`;
                document.querySelector(".ctn-ciudades").innerHTML = string;
                const btn = document.querySelectorAll(".btn-delete");
                for (let i = 0; i < btn.length; i++) {
                    btn[i].addEventListener("click", function() {
                        deleteEstudiante(btn[i].id)
                    });
                }
            })
            .catch(error => console.error(error));
    }

    document.querySelector(".btn-search").addEventListener('click', function() {
        let id = document.querySelector(".input-search").value;
        filter(id);
    })
    getCiudades();
});