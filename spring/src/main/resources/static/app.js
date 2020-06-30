var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    // var socket = new SockJS('localhost:8080/api/websocket/lesson?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZGVudGl0eSI6IjVlZWQ4MDM1N2I0OGJlNGJkMjdmOGQ3OCIsImlzcyI6IuW8k-W8puaVmeiCsiIsImV4cCI6MTU5MjczMjk3MywiaWF0IjoxNTkyNzI5MzczfQ.gR86XDEdjotQ3lltAua6ZYD_L_Q423Febpca66r_oWMO8mkwPJJQfav2Y9f1NX3FJIufTgvOlrTlBMrUYx7FeA');
    var url = '/api/websocket/lesson?lessonId=' + $("#lesson").val() + '&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZGVudGl0eSI6IjVlZWQ4MDM1N2I0OGJlNGJkMjdmOGQ3OCIsImlzcyI6IuW8k-W8puaVmeiCsiIsImV4cCI6MTU5MzI0ODA5NiwiaWF0IjoxNTkzMjQ0NDk2fQ.fvoLoQrTs520oVJhByKEqca_9Pj3h2cKYOn05FN-GJp58bzmE0fhCUzvomxHfGJiLyVWP0yP1JfOcaneS5LDQA';
    var socket = new SockJS(url);
    // var socket = new SockJS('/api/websocket/lesson?lessonId=123');
    stompClient = Stomp.over(socket);
    /*    var headers = {
            token: 'Bearer 1234567'
        };*/
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat/' + $("#lesson").val(), function (greeting) {
            showGreeting(JSON.parse(greeting.body).context);
            showGreeting(JSON.parse(greeting.body).headCount);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/chat/" + $("#lesson").val(), {}, JSON.stringify({'context': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});

