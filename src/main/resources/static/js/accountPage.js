function showNotesModal(id) {

    var entryData = {
        id : id
    };

    $.ajax({
        url: '/account/notes',
        data: entryData,
        dataType: 'json'
    }).done(function (response) {
        printNotes(response.notes);
        // $('#note-modal .modal-body').html(printNotes(response.notes));
    });
    $('#note-modal .modal-body').text('');
    $('#note-modal').modal('show');
}

function printNotes(array) {
    ul = document.createElement('ul');

    document.getElementById('modal-body').appendChild(ul);

    array.forEach(function (el) {
        var li = document.createElement('li');
        ul.appendChild(li);

        li.innerHTML += el.note;
    });
}

