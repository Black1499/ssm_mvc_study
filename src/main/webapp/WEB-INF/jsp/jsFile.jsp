<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="file" multiple="multiple" id="myFile"/>
<br/>
<canvas id="myCanvas"></canvas>
<br/>
<button id="myBtn">上传</button>
<progress value="0" max="100" id="myPro"></progress>
</body>
<script type="text/javascript" src="../js/jquery.js"></script>
<script>

    var myFile = document.getElementById("myFile");
    var myBtn = document.getElementById("myBtn");
    var myCanvas = document.getElementById("myCanvas");
    window.addEventListener("load", () => {
        all();
    });

    function all() {
        myFile.addEventListener("change", () => {
            $("#myPro").val(0);
            loadImg();
        });
        myBtn.addEventListener("click", () => {
            ajax(myFile.files[0]);
        });
    }


    var myImg = new Image();
    var pen = myCanvas.getContext("2d");

    function loadImg(callback) {
        var url = URL.createObjectURL(myFile.files[0]);
        myImg.src = url;
        //console.log(myImg);
        console.log("压缩前：" + myFile.files[0].size);
        // 这里是异步
        myImg.onload = function () {
            URL.revokeObjectURL(url);
            // 压缩图片
            myCanvas.width = myImg.width / 2;
            myCanvas.height = myImg.height / 2;
            pen.drawImage(myImg, 0, 0, myImg.width / 2, myImg.height / 2);
            pen.font = "30px yahei";
            pen.fillText("lzx绘制", (myImg.width / 2) - 100, (myImg.height / 2) - 10);
            if (callback) () => callBack();
        }
    }


    function ajax(blob) {
        var form = new FormData();
        form.append("file", blob);
        $.ajax({
            url: '/jsFile',
            type: 'post',
            data: form,
            cache: false,
            contentType: false, //必须false才会自动加上正确的Content-Type
            processData: false,  //必须false才会避开jQuery对 formdata 的默认处理
            xhr: function () {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // check if upload property exists
                    myXhr.upload.addEventListener('progress', function (e) {
                        var loaded = e.loaded;//已经上传大小情况
                        var total = e.total;//附件总大小
                        var per = Math.floor(100 * loaded / total);  //已经上传的百分比
                          if (e.lengthComputable) {
                            $("#myPro").val(per);
                            //清空画板
                            pen.clearRect(0, 0, myCanvas.width, myCanvas.height);
                            //从左下角不停地画
                            pen.globalAlpha = 0.5;
                            pen.drawImage(myImg, 0, myCanvas.height, myCanvas.width, -myCanvas.height * per/100);
                            pen.font = "30px yahei";
                            pen.fillText("lzx绘制", (myImg.width / 2) - 100, (myImg.height / 2) - 10);
                            //显示百分比
                            pen.font = "80px yahei";
                            pen.fillText(per + "%", myCanvas.width / 2 - 20, myCanvas.height / 2 - 20);

                        }
                    }, false);
                }
                return myXhr;
            }
        });
    }
</script>
</html>
