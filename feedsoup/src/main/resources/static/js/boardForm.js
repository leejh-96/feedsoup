$(document).ready(function(){
    findByBoardCategory();
});

document.getElementById("board-category-select").addEventListener("change",function (){
    let value = $('#board-category-select > option:selected').val();
    valueCheck(value);
});

function findByBoardCategory(){
    $.ajax({
        url: '/board/findByBoardCategory',
        type: 'get',
        success: function (obj){
            JSON.stringify(obj);
            $('#board-category-select').append('<option value="0" selected>------------선택------------</option>');
            $('#board-option-select').append('<option selected>------------선택------------</option>');
            for (let i = 0; i < obj.length; i++) {
                $('#board-category-select').append('<option value="' + obj[i].boardCategoryNo + '"name="boardCategoryNo">' + obj[i].boardCategoryName + '</option>');
            }
        },
        error: function (errors){
            alert('잠시 후 다시 시도해주세요.');
        }
    });
}

function valueCheck(value){
    if (value === 0){
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
           for (let i = 0; i < obj.length; i++){
               $('#board-option-select').append('<option value="' + obj[i].boardOptionNo + '" name="boardOptionNo">' + obj[i].boardOptionType + '</option>');
           }
       },
        error: function (errors){
           alert('잠시 후 다시 시도해주세요.');
        }
    });
}

function optionSettings(){
    $('#board-option-select').empty();
    $('#board-option-select').append('<option selected>------------선택------------</option>');
}
let maxFileAttachment = 10;//첨부파일 최대 갯수
let fileFieldCount = 1;//초기 첨부파일 갯수
document.getElementById("option-add-btn").addEventListener("click",function (){
    if (maxFileAttachment > fileFieldCount){
        optionAdd();
        fileFieldCount++;
    }else{
        alert('더 이상 추가할 수 없습니다.');
    }
});

function optionAdd(){
    let newFileInput =
        '<tr>'+
        '<th class="align-middle">첨부파일</th>'+
        '<td colspan="5" class="align-middle input-group">'+
        '<input name="files" class="form-control" type="file">'+
        '</td>'+
        '</tr>';
    $('#file-attachment').append(newFileInput);
}

document.getElementById("option-delete-btn").addEventListener("click",function (){
    if (fileFieldCount === 1){
        alert('더 이상 삭제할 수 없습니다.');
    }else {
        $('#file-attachment>tr:last').remove();
        fileFieldCount--;
    }
});