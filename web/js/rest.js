function request(url, type, data) {

    var xhr = new XMLHttpRequest();
    xhr.open(type, url, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

    xhr.send(JSON.stringify(data));

    xhr.onload = function() {

        try {
            return JSON.parse(xhr.responseText);
        } catch(error) {
            console.error('Response is not JSON');
        }

    };
}