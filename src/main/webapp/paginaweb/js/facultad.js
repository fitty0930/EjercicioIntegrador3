document.addEventListener("DOMContentLoaded", function() {
    function getFacultades() {
        let myHeaders = new Headers();
        let requestOptions = {
            method: 'GET',
            redirect: 'follow',
        };

        //ESTO SE AVANZA EN LA SEGUNDA PARTE 
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/facultades", requestOptions)
            .then(response => response.json())
            .then(data => {
                let string = ""
                data.forEach(facultad => {
                    string += `<li href="#" class="list-group-item text-left">
                        <div class="contentEstudiente">
                            <img class="img-thumbnail "
                                src="https://bootdey.com/img/Content/User_for_snippets.png">
                            <label class="name ms-2">
                                ${facultad.nombreFacultad}<br>
                            </label>
                        </div>
                        <div class="abmEstudient">
                            <span class="pull-right ">
                                <span><i class="fas fa-users mt-4"></i> 39</span>
                                <a class="btn-delete"  id="${facultad.idFacultad}" type="button"><i class="fas fa-trash-alt color-danger ms-3 mt-4"></i></a>
                            </span>
                        </div>
                        <!-- <div class="break"></div> -->
                    </li>`;
                });
                document.querySelector(".ctn-facultades").innerHTML = string;
                const btn = document.querySelectorAll(".btn-delete");
                for (let i = 0; i < btn.length; i++) {
                    btn[i].addEventListener("click", function() {
                        deleteFacultad(btn[i].id)
                    });
                }
            })
            .catch(error => console.error(error));
    }

    function deleteFacultad(id) {
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", 'application/json');
        myHeaders.append("Access-Control-Allow-Origin", "*");
        let requestOptions = {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow',
        };
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/facultades/" + id, requestOptions)
            .then(res => {
                console.log(res);
                getFacultades();
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

        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes" + "/facultades/searchname/" + idEstudiante + "", requestOptions)
            .then(response => response.json())
            .then(facultad => {

                let string = `<li href="#" class="list-group-item text-left">
                    <div class="contentEstudiente">
                        <img class="img-thumbnail " src="https://bootdey.com/img/Content/User_for_snippets.png">
                        <label class="name ms-2">
                            ${facultad.nombreFacultad}<br>
                      </label>
                  </div>
                  <div class="abmEstudient">
                      <span class="pull-right">
                          <i class="far fa-eye mt-4"></i>
                          <a class="btn-delete" id="${facultad.idFacultad}" type="button"><i class="fas fa-trash-alt color-danger ms-3"></i></a>
                          <i class="fas fa-envelope-square ms-3"></i>
                      </span>
                  </div>
                  <!-- <div class="break"></div> -->
                </li>`;
                document.querySelector(".ctn-facultades").innerHTML = string;
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

    getFacultades();
});