function deleteFile(boardNo,fileNo) {

    alert(boardNo+','+fileNo);
    if (!confirm('정말로 삭제하시겠습니까? 삭제한 파일은 복구가 불가능합니다.')) {
        return alert('취소되었습니다.');
    }

    if (!checkFileParameter(boardNo,fileNo)) {
        return alert('올바른값이 아닙니다.');
    }

    $.ajax({
        url: '/board/delete/' + boardNo + '/' + fileNo,
        method: 'delete',
        success: function (response, status, xhr) {
            if (xhr.status === 200) {
                alert('파일이 삭제되었습니다.');
                let redirectUrl = '/board/updateForm/' + boardNo;
                location.href = redirectUrl;
            } else if (xhr.status === 404) {
                alert('파일 삭제에 실패했습니다.');
            } else {
                alert('올바른 요청이 아닙니다!');
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            alert('통신 에러가 발생했습니다.');
            console.error('통신에러:', xhr.status, textStatus, errorThrown);
        }
    });
}
function checkFileParameter(boardNo,fileNo) {
    // 숫자 형태인지 체크
    if (isNaN(boardNo)) {
        console.log('boardNo는 숫자가 아닙니다.');
        return false;
    }
    if (isNaN(fileNo)){
        console.log('replyNo는 숫자가 아닙니다.');
        return false;
    }
    return true;
}