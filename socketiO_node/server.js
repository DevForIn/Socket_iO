<<<<<<< HEAD
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.send('<h1>안녕하세요 "/" 경로 입니다.</h1>');
});

io.on('connection', function(socket){
  console.log('한명의 유저가 접속을 했습니다.');
  socket.on('disconnect', function () {
    console.log('한명의 유저가 접속해제를 했습니다.');
  });
  socket.on('send_msg', function (msg) {
    //콘솔로 출력을 한다.
    console.log(msg);
    //다시, 소켓을 통해 이벤트를 전송한다.
    io.emit('send_msg', msg);
  });
});

http.listen(82, function(){
  console.log('listening on *:82');
});
=======
var app = require("express")();
var http = require("http").createServer(app);
var io = require('socket.io')(http);

var port = 3000;
http.listen(port, () => {
  console.log("listening on *:" + port);
});

io.on('connection', function (socket) {
  console.log(socket.id, 'Connected');

  socket.emit('msg', `${socket.id} 연결 되었습니다.`);
  
  socket.on('msg', function (data) {
    console.log(socket.id, data);
    
    socket.emit('msg', `Server : "${data}" 받았습니다.`);
  });
});

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
})
>>>>>>> 33ab0241b90f6eb6f2b9235c0ce3e4beaab614e1
