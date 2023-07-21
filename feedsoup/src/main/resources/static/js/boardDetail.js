window.addEventListener('load', function() {
    // 페이지 로드 시 textarea의 높이 초기화
    autoResizeTextArea();
});
function autoResizeTextArea() {
    let formTextarea = document.getElementById('form-textarea');
    formTextarea.style.height = 'auto'; // 높이를 임시적으로 auto로 설정하여 내용에 맞게 자동 조절
    formTextarea.style.height = formTextarea.scrollHeight + 'px'; // 컨텐츠에 맞게 높이 설정
}
function toggleContent(replyNum) {

    let contentDiv = document.getElementById('collapse-' + replyNum);

    if (contentDiv.style.display === 'none') {
        contentDiv.style.display = 'block';

        let textarea = document.getElementById('dynamic-textarea' + replyNum);
        textarea.style.height = 'auto'; // 높이를 임시적으로 auto로 설정하여 내용에 맞게 자동 조절
        textarea.style.height = textarea.scrollHeight + 'px'; // 컨텐츠에 맞게 높이 설정
        textarea.style.width = '100%';
    } else {
        contentDiv.style.display = 'none';
    }
}

function deleteReply(boardNo,replyNo){

    if (!confirm('정말로 삭제하시겠습니까? 삭제한 댓글은 복구가 불가능합니다.')){
        return alert('취소되었습니다.');
    }

    if (!checkParameters(boardNo,replyNo)){
        return alert('올바른값이 아닙니다.');
    }

    $.ajax({
        url: '/reply/' + boardNo + '/' + replyNo,
        method: 'delete',
        success: function (response,status, xhr) {
            if (xhr.status === 200) {
                alert('댓글이 삭제되었습니다.');
                let page = 1;
                let redirectUrl = '/board/' + boardNo + '/' + page;
                location.href = redirectUrl;
            }else if (xhr.status === 404) {
                alert('댓글 삭제에 실패했습니다.');
            }else {
                alert('올바른 요청이 아닙니다!');
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            alert('통신 에러가 발생했습니다.');
            console.error('통신에러:', xhr.status, textStatus, errorThrown);
        }
    });

}

function checkParameters(boardNo, replyNo) {
    // 숫자 형태인지 체크
    if (isNaN(boardNo) && isNaN(replyNo)) {
        console.log('boardNo와 replyNo는 숫자가 아닙니다.');
        return false;
    }

    // null 값 또는 빈 값인지 체크
    if (boardNo === null || boardNo === '') {
        console.log('boardNo는 null 값이거나 빈 값입니다.');
        return false;
    }

    if (replyNo === null || replyNo === '') {
        console.log('replyNo는 null 값이거나 빈 값입니다.');
        return false;
    }
    return true;
}
function deleteBoard(boardNo){

    if (!confirm('정말로 삭제하시겠습니까? 삭제한 게시물은 복구가 불가능합니다.')){
        return alert('취소되었습니다.');
    }

    if (!checkBoardNoParameters(boardNo)){
        return alert('올바른값이 아닙니다.');
    }

    $.ajax({
        url: '/board/'+ boardNo,
        method: 'delete',
        success:function (response,status,xhr){
            if (xhr.status === 200) {
                alert('게시글이 삭제되었습니다.');
                let redirectUrl = '/board';
                location.href = redirectUrl;
            }else if (xhr.status === 404) {
                alert('게시글 삭제에 실패했습니다.');
            }else {
                alert('올바른 요청이 아닙니다!');
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            alert('통신 에러가 발생했습니다.');
            console.error('통신에러:', xhr.status, textStatus, errorThrown);
        }
    });
}
function checkBoardNoParameters(boardNo) {
    // 숫자 형태인지 체크
    if (isNaN(boardNo)) {
        console.log('boardNo와 replyNo는 숫자가 아닙니다.');
        return false;
    }

    // null 값 또는 빈 값인지 체크
    if (boardNo === null || boardNo === '') {
        console.log('boardNo는 null 값이거나 빈 값입니다.');
        return false;
    }
    return true;
}