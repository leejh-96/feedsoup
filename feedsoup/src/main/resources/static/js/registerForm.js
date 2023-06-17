let count = 0;
let emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;

function emailCheck(value){
    console.log(value);

    if (!emailRegex.test(value)){
        document.getElementById("emailMessage").style.display = "block";
        document.getElementById("emailValidate").style.display = "none";
        return document.getElementById("email").focus();
    }else{
        document.getElementById("emailMessage").style.display = "none";
        document.getElementById("emailValidate").style.display = "block";
        return document.getElementById("emailValidateBtn").focus();
    }
}

function passwordCheck(value){
    console.log(value);

    if (!passwordRegex.test(value)){
        if (document.getElementById("passCheckMessage").style.display === "block"
        || document.getElementById("passCheckValidate").style.display === "block"
        ){
            document.getElementById("passCheckMessage").style.display = "none";
            document.getElementById("passCheckValidate").style.display = "none";
        }

        document.getElementById("passwordMessage").style.display = "block";
        document.getElementById("passwordValidate").style.display = "none";
        return document.getElementById("password").focus();
    }else{
        document.getElementById("passwordMessage").style.display = "none";
        document.getElementById("passwordValidate").style.display = "block";
        return document.getElementById("passCheck").focus();
    }
}

function passCheckAgain(value){
    console.log(value);

    let originalPassword = document.getElementById("password");
    console.log(originalPassword.value);

    if (!passwordRegex.test(value)){
        document.getElementById("passCheckMessage").style.display = "block";
        document.getElementById("passCheckValidate").style.display = "none";
        return document.getElementById("passCheck").focus();
    }

    if (value === originalPassword.value){
        document.getElementById("passCheckMessage").style.display = "none";
        document.getElementById("passCheckValidate").style.display = "block";
    }else{
        document.getElementById("passCheckMessage").style.display = "block";
        document.getElementById("passCheckValidate").style.display = "none";
        return document.getElementById("passCheck").focus();
    }
}