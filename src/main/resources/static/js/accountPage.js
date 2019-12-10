$(document).ready(function() {
    var date = $("#visit-date");
    var time = $("#visit-time");
    var msg = $("#message");

    var error_message = "Please choose date";

    time.attr("disabled", "disabled");

    date.blur(function() {
        if ($(this).val() != '') {
            var doctorId = $("#doctor :selected")[0].value;
            var dateVisit = $("#visit-date")[0].value;
            callForFreeHours(doctorId, dateVisit);

            time.removeAttr("disabled");
            msg.html("");
        }
        else {
            time.attr("disabled", "disabled");
            if(msg[0].innerText === '') {
                msg.append(error_message);
            }
        }
    });
});

function callForFreeHours(doctorId, date) {
    var entryData = {
        id : doctorId,
        date : date
    };

    $.ajax({
        url: '/account/free-hours',
        data: entryData,
        dataType: 'json'
    }).done(function (response) {
        console.log("request success");

        select = $("#visit-time");
        var temp = '';
        for(hour in response.hours) {
            temp += '<option value=\"' + response.hours[hour] + '\">' + response.hours[hour] + '</option>\n';
        }
        select.html(temp);
    });
}

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

