document.getElementById('#register-form').on('submit', function (e) {
    e.preventDefault();

    var isFormValid = true;
    var elements = document.getElementById('#register-form').querySelectorAll('[required]');

    var name = document.getElementById('#reg-name');
    var surname = document.getElementById('#reg-surname');
    var email = document.getElementById('#reg-email');
    var pesel = document.getElementById('#reg-pesel');
    var dateOfBirth = document.getElementById('#reg-birth');
    var phone = document.getElementById('#reg-phone');
    var password = document.getElementById('#reg-password');
    var matchPassword = document.getElementById('#reg-mpassword');

    [].forEach.call(elements, function (element) {
        var type = element.type;

        if(type === 'text') {
            test
        }
        else if(type === 'email') {

        }
        else if(type === 'date'){

        }
        else if(type === 'password'){

        }

        if (isFormValid) {
            e.submit();
        } else {
            return false;
        }
    })
});

function validText(input) {
    var regexp = '/[£!@#$%^&*()_+~`,./<>?;\':"|\\\\\\[\\]\\{\\}]\\w+/g'
    var isTextValid = true;

    if(input.test(regexp)) {
        isTextValid = false;
    }

    return isTextValid;
}

function validEmail(input) {
    
}

function validDate(input) {
    
}

function showFieldValidation(input, inputIsValid) {
    if (!inputIsValid) {
        input.parentElement.classList.add(this.options.classError);
    } else {
        input.parentElement.classList.remove(this.options.classError);
    }
};