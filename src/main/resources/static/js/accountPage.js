function showNotesModal(id) {

    $('#modal-visit-id').text(id);

    var entryData = {
        id : id
    };

    $.ajax({
        url: '/account/notes',
        data: entryData,
        dataType: 'json'
    }).done(function (response) {
        printNotes(response.notes);
    });
    $('#modal-notes-list').text('');
    $('#note-modal').modal('show');
}

function printNotes(array) {
    ul = document.createElement('ul');

    document.getElementById('modal-notes-list').appendChild(ul);

    array.forEach(function (el) {
        var li = document.createElement('li');
        ul.appendChild(li);

        li.innerHTML += el.note;
    });
}

$('#add-note-btn').on('click', function () {

    var note = $('#message-text')[0].value;
    var id = $('#modal-visit-id')[0].innerText;

    if (note === '') {
        return 0;
    }

    var entryData = {
        id : id,
        note : note
    };

    $.ajax({
        url: '/account/add-note',
        data: entryData,
        dataType: 'json'
    }).done(function () {
        $('#modal-visit-id').text('');
    });
});

