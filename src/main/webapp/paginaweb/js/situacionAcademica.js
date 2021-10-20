document.addEventListener("DOMContentLoaded", function() {
    let situacionesAcademicas = [];

    function getReportesCarrera() {
        let myHeaders = new Headers();
        let requestOptions = {
            method: 'GET',
            redirect: 'follow',
        };

        //ESTO SE AVANZA EN LA SEGUNDA PARTE 
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/situaciones/", requestOptions)
            .then(response => response.json())
            .then(data => {
                let string = "";
                data.forEach(situacionAcademica => {
                    string += `<li href="#" class="list-group-item text-left">
                    <div class="contentEstudiente">
                        <img class="img-thumbnail mb-3"
                            src="https://bootdey.com/img/Content/User_for_snippets.png">
                        <label class="name ms-4">
                            <h6>Estudiante</h6>
                            <p>${situacionAcademica.estudiante.nombre} <span> </span> ${situacionAcademica.estudiante.nombre} </p>
                            <p>${situacionAcademica.estudiante.dni}</p>
                            <p>${situacionAcademica.estudiante.genero}</p>

                        </label>
                        <label class="name ms-4">
                            <h6>Carrera</h6>
                            <p>${situacionAcademica.carrera.nombreCarrera}</p>
                        </label>
                        <label class="name ms-4">
                            <h6>Facultad</h6>
                            <p>${situacionAcademica.carrera.facultad.nombreFacultad}</p>
                        </label>
                    </div>
                    <div class="abmEstudient">
                        <span class="pull-right ">
                            <i class="far fa-eye mt-4"></i>
                            <a class="btn-delete"  id="${situacionAcademica.id}" type="button"><i class="fas fa-trash-alt color-danger ms-3 mt-4"></i></a>
                        </span>
                    </div>
                    <!-- <div class="break"></div> -->
                </li>`;
                });
                document.querySelector(".ctn-situacionAcademica").innerHTML = string;
                const btn = document.querySelectorAll(".btn-delete");
                for (let i = 0; i < btn.length; i++) {
                    btn[i].addEventListener("click", function() {
                        deleteSituacionAcademica(btn[i].id)
                    });
                }
            })
            .catch(error => console.error(error));
    }

    function deleteSituacionAcademica(id) {
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", 'application/json');
        myHeaders.append("Access-Control-Allow-Origin", "*");
        let requestOptions = {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow',
        };
        fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/situaciones/" + id, requestOptions)
            .then(res => {
                console.log(res);
                getReportesCarrera();
            })
            .catch((error) => console.log(error))
    }
    getReportesCarrera();
});