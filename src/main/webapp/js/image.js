function drawImageWithPath(jsonName, containerId) {
    //152294.json
    $.getJSON(jsonName, function (data) {

        //todo: on image change size listener

        drawImage(containerId, data.imageLink);

        $("#" + containerId + " img").load(function () {
            var imageWidth = $(this).width();
            var imageHeight = $(this).height();
            drawLine(containerId, data.line, imageWidth, imageHeight)
        });


    });
}

function drawLine(containerId, lineObject, imageWidth, imageHeight) {
    var plotWindth = lineObject.plotWindth;
    var plotHeight = lineObject.plotHeight;
    var points = lineObject.points;

    var ratio = getRatio(imageWidth, plotWindth);

    if (ratio !== 1) {
        points.forEach(function (point) {
            point.x = point.x * ratio;
            point.y = point.y * ratio;
        });
    }

    var canvasContainer = d3.select("#canvas-container");

    var canvas = canvasContainer.append("svg:svg")
        .attr("width", imageWidth)
        .attr("height", imageHeight);


    var d3line2 = d3.svg.line()
        .x(function (d) {
            return d.x;
        })
        .y(function (d) {
            return d.y;
        })
//            .interpolate("linear");
        .interpolate("basis");


    canvas.append("svg:path")
        .attr("d", d3line2(points))
        .style("stroke-width", 2)
        .style("stroke", "#ff0000")
        .style("fill", "none");


}

function drawImage(containerId, imagePath) {
    var imageName = getImageName(imagePath);
    $("#" + containerId).append($("<img>").attr({"src": imageName, "width": "500px"}));

}


function getImageName(imagePath) {
    var start = imagePath.lastIndexOf("/");
    return imagePath.substring(start + 1, imagePath.length);
}

function getRatio(imageWidth, plotWindth) {
    if (imageWidth !== plotWindth) {
        return imageWidth / plotWindth;
    }
    return 1;
}

