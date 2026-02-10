const lettereMinuscole = [
  "a","b","c","d","e","f","g","h","i","j",
  "k","l","m","n","o","p","q","r","s","t",
  "u","v","w","x","y","z"," "
];

const lettereMaiuscole = [
  "A","B","C","D","E","F","G","H","I","J",
  "K","L","M","N","O","P","Q","R","S","T",
  "U","V","W","X","Y","Z"
];

const numeri = ["0","1","2","3","4","5","6","7","8","9"];

const caratteriSpeciali = ["!","?","-","_","$","(",")","[","]","{","}"];


function checkForm(){
  let errorFlag = 0;
  let finalMessage = ""
  let errorMessage = "<div>"
  let nome = document.getElementById("usernameInput").value.toLowerCase()
  let eta = document.getElementById("ageInput").value
  let password = document.getElementById("passwordInput").value
    
  if(nome.length < 1){
    errorMessage += "<p>Devi Inserire un Nome!!</p>"
  }
  else{
    for(let i = 0; i < nome.length; i++){
      if(!lettereMinuscole.includes(nome[i])){
        errorMessage += "<p>Il Nome Deve Contenere Solo Lettere o Spazi!!</p>"
        errorFlag = 1;
        break
      }
    }
  }



  if(eta < 18 || eta > 100 || isNaN(eta)) {
    errorFlag = 1;
    if(isNaN(eta)){
      errorMessage += "<p>L'Età Deve Essere un Numero!!</p>"
    }
    else{
      errorMessage += "<p>L'Età Deve Essere Compresa Tra 18 e 100!!</p>"
    }
  }



  if(password.length < 8 || password.length > 20) {
    errorFlag = 1;
    if(password.length < 8){
      errorMessage += "<p>Password Troppo Corta, Minimo 8 Caratteri!!</p>"
    }
    else {
      errorMessage += "<p>Password Troppo Lunga, Massimo 20 Caratteri!!</p>"
    }
  }
  else{
    let lFlag = 0;
    let hFlag = 0;
    let nFlag = 0;
    let sFlag = 0;
    let wrongFlag = 0;

    for(let i = 0; i < password.length; i++){
      if(lettereMinuscole.includes(password[i])) {
        lFlag = 1;
      }
      else if(lettereMaiuscole.includes(password[i])) {
        hFlag = 1;
      }
      else if(numeri.includes(password[i])) {
        nFlag = 1;
      }
      else if(caratteriSpeciali.includes(password[i])) {
        sFlag = 1;
      }
      else {
        if(wrongFlag == 0){
          errorMessage += "<p>La Password Contiene Caratteri Non Ammessi!!<br>-Lista Caratteri Speciali: !?-_$()[]{}</p>"
          wrongFlag = 1
        }
      }
    }
  
    if(lFlag == 0){
      errorFlag = 1;
      errorMessage += "<p>La Password Deve Contenere Almeno un Carattere Minuscolo!!</p>"
    }
    if(hFlag == 0){
      errorFlag = 1;
      errorMessage += "<p>La Password Deve Contenere Almeno un Carattere Maiuscolo!!</p>"
    }
    if(nFlag == 0){
      errorFlag = 1;
      errorMessage += "<p>La Password Deve Contenere Almeno un Numero!!</p>"
    }
    if(sFlag == 0){
      errorFlag = 1;
      errorMessage += "<p>La Password Deve Contenere Almeno un Carattere Speciale!!<br>-Lista Caratteri Speciali: !?-_$()[]{}</p>"
    }
  }



  const consenso = document.querySelector('input[name="consenso"]:checked');
  if(!consenso) {
    errorFlag = 1;
    errorMessage += "<p>Per Accedere Devi Selezionare Il Consenso dei Dati!!</p>"
  }

  errorMessage += "</div"

  if(errorFlag == 0){
    finalMessage = "<div class='correct'><p>Dati Corretti</p></div>"
  }
  else{
    finalMessage = "<div class='error'>" + errorMessage + "</div>"
  }

  document.getElementById("errori").innerHTML = finalMessage
}
