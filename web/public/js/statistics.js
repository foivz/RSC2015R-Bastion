/**
 * Created by jtrupina on 22.11.15..
 */

function visitorData (data) {
    $('#chart1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Average Visitors'
        },
        xAxis: {
            categories: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
        },
        yAxis: {
            title: {
                text: 'Number of visitors'
            }
        },
        series: data,
    });
}
$(document).ready(function() {
    $.ajax({
        url: '/admin',
        type: 'GET',
        async: true,
        dataType: "json",
        success: function (data) {
            visitorData(data);
        }
    });
});
