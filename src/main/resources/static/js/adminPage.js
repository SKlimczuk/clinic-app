function editDoctor(id) {
    $('#doctor-id').text(id);

    var entryData = {
        id : id
    };

    $.ajax({
        url: '/admin/doctor',
        data: entryData
    }).done(function (response) {
        document.getElementById('updd-name').placeholder = response.user.name;
        document.getElementById('updd-surname').placeholder = response.user.surname;
        document.getElementById('updd-specialization').placeholder = response.user.surname;
        document.getElementById('updd-email').placeholder = response.user.email;
        document.getElementById('updd-phone').placeholder = response.user.phone;
        document.getElementById('updd-pesel').placeholder = response.user.pesel;
    });

    $('#update-doctor').modal('show');
}

$('#update-doctor-btn').on('click', function () {

    var id = $('#doctor-id')[0].innerText;

    var name = document.getElementById('updd-name').value;
    var surname = document.getElementById('updd-surname').value;
    var specialization = document.getElementById('updd-specialization').value;
    var email = document.getElementById('updd-email').value;
    var phone = document.getElementById('updd-phone').value;
    var pesel = document.getElementById('updd-pesel').value;
    var password = document.getElementById('updd-password').value;

    var entryData = {
        id : id,
        name : name,
        surname : surname,
        specialization : specialization,
        email : email,
        phone : phone,
        pesel : pesel,
        password : password
    };

    $.ajax({
        url: '/admin/update/patient',
        data: entryData,
        dataType: 'json'
    }).done(function () {
        location = location.href;
    });
});

function editPatient(id) {
    $('#patient-id').text(id);

    var entryData = {
        id : id
    };

    $.ajax({
        url: '/admin/user',
        data: entryData
    }).done(function (response) {
        document.getElementById('upd-name').placeholder = response.user.name;
        document.getElementById('upd-surname').placeholder = response.user.surname;
        document.getElementById('upd-email').placeholder = response.user.email;
        document.getElementById('upd-phone').placeholder = response.user.phone;
        document.getElementById('upd-pesel').placeholder = response.user.pesel;
    });

    $('#update-patient').modal('show');
}

$('#update-patient-btn').on('click', function () {

    var id = $('#patient-id')[0].innerText;

    var name = document.getElementById('upd-name').value;
    var surname = document.getElementById('upd-surname').value;
    var email = document.getElementById('upd-email').value;
    var phone = document.getElementById('upd-phone').value;
    var pesel = document.getElementById('upd-pesel').value;
    var password = document.getElementById('upd-password').value;

    var entryData = {
        id : id,
        name : name,
        surname : surname,
        email : email,
        phone : phone,
        pesel : pesel,
        password : password
    };

    $.ajax({
        url: '/admin/update/patient',
        data: entryData,
        dataType: 'json'
    }).done(function () {
        location = location.href;
    });
});

function deleteUser(id) {
    var entryData = {
        id : id
    };

    $.ajax({
        url: '/admin/remove',
        data: entryData
    }).done(function (response) {
        location = location.href;
        console.log("delete success")
    });
}