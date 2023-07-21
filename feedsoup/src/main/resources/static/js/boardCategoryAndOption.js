// =============select option js Start=============
$(document).ready(function(){
    optionInitialSetting();
});
function optionInitialSetting(){
    $('#board-option-select').append('<option value="0" selected>------------선택------------</option>');
}

document.getElementById("board-category-select").addEventListener("change",function (){
    let value = $('#board-category-select > option:selected').val();
    valueCheck(value);
});
function valueCheck(value){
    if (value === 0 || value === '0'){
        optionSettings();
        return;
    }
    findByBoardOption(value);
}
function findByBoardOption(value){
    $.ajax({
        url: '/board/findByBoardOption',
        type: 'get',
        data: {boardCategoryNo : value},
        success: function (obj){
            JSON.stringify(obj);
            optionSettings();
            let check = false;

            for (let i = 0; i < obj.length; i++){
                if (obj[i].boardOptionNo === undefined || obj[i].boardOptionNo === null || obj[i].boardOptionNo === "" || obj[i].boardOptionNo === ''){
                    //세션이 끊어진 상태를 체크함
                    alert('다시 로그인이 필요합니다.');
                    break;

                }else{
                    //세션이 유효하다면 option을 그린다.
                    $('#board-option-select').append('<option value="' + obj[i].boardOptionNo + '" name="boardOptionNo">' + obj[i].boardOptionType + '</option>');
                }
                check = true;
            }
            if (!check) window.location.href="/loginForm";

        },
        error: function (errors){
            alert('잠시 후 다시 시도해주세요.');
        }
    });
}
function optionSettings(){
    $('#board-option-select').empty();
    $('#board-option-select').append('<option value="0" selected>------------선택------------</option>');
}
// =============select option js End=============