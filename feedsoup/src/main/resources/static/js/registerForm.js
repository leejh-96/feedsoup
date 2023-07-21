document.getElementById("memberId").addEventListener("keyup", function() {
    $('#email-verify-span').empty();
});
document.getElementById('checkNum').addEventListener('keyup',function (){
    $('#email-verify-num-span').empty();
});
document.getElementById("email-verify-btn").addEventListener("click", function() {
    let memberId = $('#memberId').val();
    $('#email-field-error').empty();
    sendMail(memberId);
});
function sendMail(memberId){
    let message = '';
    if (emailCheck(memberId)){
        sendMailToServer(memberId,message);
    }else {
        message = '이메일을 형식에 맞게 작성해주세요.';
        $('#email-verify-span').text(message);
    }
}

let emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
function emailCheck(memberId){
    console.log(memberId);
    //값이 유효하지 않은 경우
    if (memberId === null || memberId === "" || !emailRegex.test(memberId)) {
        return false;
    }
    return true;
}

function sendMailToServer(memberId,message){
    $.ajax({
        url: '/sendMail',
        method: 'post',
        data: {memberId : memberId},
        success: function (status){
            let result = status['result'];
            if (result === 'createSession'){
                message = '메일로 인증번호가 전송되었습니다.';
            }else if (result === 'createSessionEmailConfirm'){
                message = '메일로 인증번호가 전송되었습니다.';
            }else if (result === 'sessionTrue'){
                message = '이미 인증번호가 전송되었습니다.';
            }else if (result === 'bindingResult'){
                message = '이메일을 형식에 맞게 작성해주세요.';
            }else if (result === 'duplicateEmail'){
                message = '이미 사용중인 이메일입니다.';
            }
            $('#email-verify-span').text(message);
        },
        error: function (error){
            alert('잠시 후 다시 시도해주세요.');
            console.log('ajax 통신 실패'+error);
        }
    });
}

document.getElementById('email-verify-num-btn').addEventListener('click',function (){
    let memberId = $('#memberId').val();
    let checkNum = $('#checkNum').val();
    let message = '';
    $('#checkNum-field-error').empty();
    validNum(memberId,checkNum,message);
})

function validNum(memberId,checkNum,message){
    $.ajax({
        url: '/validNum',
        method: 'post',
        data: {memberId : memberId , checkNum : checkNum},
        success: function (status) {
            let result = status['result'];
            if (result === 'sessionNull'){
                message = '인증번호가 유효하지 않습니다. 다시 인증번호를 발급받아주세요.';
            }else if (result === 'sessionEmailConfirmDTONull'){
                message = '인증번호가 유효하지 않습니다. 다시 인증번호를 발급받아주세요.';
            }else if (result === 'validFalse'){
                message = '이메일과 인증번호가 올바르지 않습니다. 다시 인증해주세요.';
            }else if (result === 'validTrue'){
                message = '인증되었습니다.';
            }
            $('#email-verify-num-span').text(message);
        },
        error: function (error){
            alert('잠시 후 다시 시도해주세요.');
            console.log('ajax 통신 실패'+error);
        }
    });
}

//주소 api
document.getElementById('post-code').addEventListener('click',function (){
    postcode();
});
function postcode(){
    new daum.Postcode({
        oncomplete: function(data) {
            $('#memberAddress').val(data.address);
        }
    }).open();
}



// document.getElementById('memberNickname').addEventListener('change',function (){
//     let memberNickname = $('#memberNickname').val();
//     duplicateNickNameCheck(memberNickname);
// })
//
// function duplicateNickNameCheck(){
//
// }

//회원가입 폼이 서버로 제출전 서버와 비동기 통신하여 세션에 이메일 인증기록과 이메일 인증상태를 검사함
// document.getElementById('submit-btn').addEventListener('click',function (){
//     submitCheck();
// })
// function submitCheck(event){
//     event.preventDefault();
//     let memberId = $('#memberId').val();
//     let checkNum = $('#checkNum').val();
//     let message = '';
//     $.ajax({
//         url: '/submitCheck',
//         method: 'post',
//         data: {memberId : memberId,checkNum : checkNum},
//         async: true,
//         success: function (status){
//             let result = status['result'];
//             if (result === 'requiredMailCheck'){
//                 message = '메일 인증이 필요한 서비스입니다.';
//                 $('#email-verify-span').text(message);
//                 return false;
//             }else if (result === null){
//                 message = '유효시간이 경과되어 다시 메일 인증이 필요합니다.';
//                 $('#email-verify-span').text(message);
//                 return false;
//             }else if (result === 'N'){
//                 message = '인증번호를 인증해주세요.';
//                 $('#email-verify-num-span').text(message);
//                 return false;
//             }else if (result === 'Y'){
//                 if (result === false){
//                     message = '인증번호가 일치하지 않습니다.';
//                     $('#email-verify-num-span').text(message);
//                     return false;
//                 }else {
//                     console.log('submit가즈아');
//                     return true;
//                 }
//             }
//         },
//         error: function (error){
//             console.log('submitCheck ajax error');
//             return false;
//         }
//     })
// }


// let count = 0;
// let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
//
// function passwordCheck(value){
//     console.log(value);
//
//     if (!passwordRegex.test(value)){
//         if (document.getElementById("passCheckMessage").style.display === "block"
//         || document.getElementById("passCheckValidate").style.display === "block"
//         ){
//             document.getElementById("passCheckMessage").style.display = "none";
//             document.getElementById("passCheckValidate").style.display = "none";
//         }
//
//         document.getElementById("passwordMessage").style.display = "block";
//         document.getElementById("passwordValidate").style.display = "none";
//         return document.getElementById("password").focus();
//     }else{
//         document.getElementById("passwordMessage").style.display = "none";
//         document.getElementById("passwordValidate").style.display = "block";
//         return document.getElementById("passCheck").focus();
//     }
// }
//
// function passCheckAgain(value){
//     console.log(value);
//
//     let originalPassword = document.getElementById("password");
//     console.log(originalPassword.value);
//
//     if (!passwordRegex.test(value)){
//         document.getElementById("passCheckMessage").style.display = "block";
//         document.getElementById("passCheckValidate").style.display = "none";
//         return document.getElementById("passCheck").focus();
//     }
//
//     if (value === originalPassword.value){
//         document.getElementById("passCheckMessage").style.display = "none";
//         document.getElementById("passCheckValidate").style.display = "block";
//     }else{
//         document.getElementById("passCheckMessage").style.display = "block";
//         document.getElementById("passCheckValidate").style.display = "none";
//         return document.getElementById("passCheck").focus();
//     }
// }