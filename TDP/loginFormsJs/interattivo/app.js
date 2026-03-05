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

document.getElementById("usernameInput").addEventListener("input", checkNome);
document.getElementById("ageInput").addEventListener("input", checkEta);
document.getElementById("passwordInput").addEventListener("input", checkPassword);

function checkNome(){
  
  let nome = document.getElementById("usernameInput").value.toLowerCase()
  const erroreNome = document.getElementById("erroreNome")

  erroreNome.innerHTML = ""

  if(nome.length < 1){
    erroreNome.innerHTML += "<p>Devi Inserire un Nome!!</p>"
    return false
  }
  else{
    for(let i = 0; i < nome.length; i++){
      if(!lettereMinuscole.includes(nome[i])){
        erroreNome.innerHTML += "<p>Il Nome Deve Contenere Solo Lettere o Spazi!!</p>"
        return false
      }
    }
  }
  
  return true
}


function checkEta(){

  let eta = document.getElementById("ageInput").value
  const errorEta = document.getElementById("errorEta")

  errorEta.innerHTML = ""

 if(isNaN(eta) && eta.length > 0) {
    errorEta.innerHTML = "<p>L'Età Deve Essere un Numero!!</p>"
    return false
  }
  else if(eta < 18) {
    errorEta.innerHTML = "<p>L'Età Deve Essere Maggiore di 18!!</p>"
    return false
  }
  else if(eta > 100) {
    errorEta.innerHTML = "<p>L'Età Deve Essere Minore di 100!!</p>"
    return false
  }

  return true
}


function checkPassword(){

  let password = document.getElementById("passwordInput").value;
  const errorPassword = document.getElementById("errorPassword");
  
  errorPassword.innerHTML = "";
  
  if(password.length < 8) {
    errorPassword.innerHTML = "<p>Password Troppo Corta, Minimo 8 Caratteri!!</p>"
    return false
  }
  if(password.length > 20) {
    errorPassword.innerHTML = "<p>Password Troppo Lunga, Massimo 20 Caratteri!!</p>"
    return false
  }
  
  let lFlag = 0;
  let hFlag = 0;
  let nFlag = 0;
  let sFlag = 0;
  let carattereNonValido = 0;
  
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
      carattereNonValido = 1;
      break;
    }
  }

  if(carattereNonValido) {
    errorPassword.innerHTML = "<p>La Password Contiene Caratteri Non Ammessi!!<br>Lista Caratteri Speciali: !?-_$()[]{}</p>"
  }
  if(!lFlag) {
    errorPassword.innerHTML += "<p>Deve contenere almeno una lettera minuscola!</p>"
  }
  if(!hFlag) {
    errorPassword.innerHTML += "<p>Deve contenere almeno una lettera maiuscola!</p>"
  }
  if(!nFlag) {
    errorPassword.innerHTML += "<p>Deve contenere almeno un numero!</p>"
  }
  if(!sFlag) {
    errorPassword.innerHTML += "<p>Deve contenere almeno un carattere speciale (!?-_$()[]{})!</p>"
  }

  return lFlag && hFlag && nFlag && sFlag
}


function checkConsenso(){

  const consenso = document.querySelector('input[name="consenso"]:checked');
  const errorConsenso = document.getElementById("errorConsenso");
  
  errorConsenso.innerHTML = "";
  
  if(!consenso) {
    errorConsenso.innerHTML = "<p>Devi Selezionare un'Opzione per il Consenso!!</p>";
    return false;
  }
  
  return true;
}


function checkForm(){
  let valido = true;
  
  if(!checkNome()) {
    valido = false
  }
  if(!checkEta()) {
    valido = false
  }
  if(!checkPassword()) {
    valido = false
  }
  if(!checkConsenso()) {
    valido = false 
  }

  const risultato = document.getElementById("errori");
  
  if(valido) {
    risultato.innerHTML = "<div class='correct'><p>Dati Corretti</p></div>";
  } else {
    risultato.innerHTML = "";
  }
}