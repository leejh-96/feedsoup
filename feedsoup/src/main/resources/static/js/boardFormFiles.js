// =============file 추가,삭제 btn js Start=============
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
// =============file 추가,삭제 btn js End=============

