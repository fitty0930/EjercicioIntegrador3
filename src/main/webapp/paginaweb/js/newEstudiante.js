document.addEventListener("DOMContentLoaded", function() {
    let name = document.getElementById("name");
    let lastName = document.getElementById("lastName");
    let dni = document.getElementById("dni");
    let genero = document.getElementById("genero");

    function createUser() {
        console.log(name.value)
    }

    document.getElementById("buttonSubmit").addEventListener("click", async (e) =>  {
        if (name.value != "" && lastName.value != "" && dni.value != "" && genero.value != "") {
			e.preventDefault();
			let dniNumber = Number(dni.value);
			let estudiante = {
		      	"nombre": name.value,
                "apellido": lastName.value,
                "dni": dniNumber,
                "genero": genero.value
		    };
		   try {
		    let response = await fetch(`http://localhost:8080/EjercicioIntegrador3/registroestudiantes/estudiantes/`, {
		      method: "POST",
		      headers: {
		        'Accept': 'application/json',
		        'Content-type': 'application/json'
		      },
		      body: JSON.stringify(estudiante)
		    });
		    let responseJson = await response.json();
		   } catch (error) {
		     console.log(error);
		   }
			
			
//			
//            let user = {
//                "nombre": "FUNCIONA HIJO DE PUTA",
//                "apellido": "paga lo que debes",
//                "dni": 675678,
//                "genero": "SABES QUE NO ME INTERESA QUE ANDES"
//            }
//            let myHeaders = new Headers();
//			myHeaders.append('Content-Type', 'application/json');
//            let requestOptions = {
//                method: 'POST',
//                headers: myHeaders,
//                body: user,
//            };
//            fetch("http://localhost:8080/EjercicioIntegrador3/registroestudiantes/estudiantes/", requestOptions)
//                .then(res => res.json())
//                .then(json => {
//                    console.log(json);
//                })
        } else {
            alert("complete todos los campos")
        }
    })
});