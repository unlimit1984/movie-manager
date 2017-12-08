<html>

<head>
    <title>Event</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        $(document).ready(function () {

            $('#addOneRow').on('click', function () {

                var html = $(".air_date_row")[0].outerHTML;
                var appendTr = jQuery(html);
                appendTr.insertAfter(".air_date_row:last");

                $('.air_date_row').each(function(i, row) {
                    $(row).find(':input').each(function(j, input) {
                        input.name = input.name.replace(/\[\d+\]/g, '['+i+']')
                    });
                });

            });
        });
    </script>
</head>

<body>
<div class="container">
    <div class="jumbotron" style="padding: 1px 20px 20px 20px; margin: 0;">
        <h2><a href="../">Main page</a></h2>
        <hr>
        <h3><a href="javascript: window.history.go(-1)">Back</a></h3>
        <h3>Event</h3>

        <form action="addEvent" method="post">
            <div class="form-group">
                <label for="id">Id:</label>
                <input type="text" class="form-control" id="id" name="id" readonly
                       value="${(event.id)!""}">
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter name"
                       required="required"
                       value="${(event.name)!""}">
            </div>
            <div class="form-group">
                <label for="basePrice">Base price:</label>
                <input type="text" class="form-control" id="basePrice" name="basePrice" placeholder="Enter Base Price"
                       required="required"
                       value="${(event.basePrice)!""}">
            </div>

        <#--Previous text mode-->
        <#--<div class="form-group">-->
        <#--<label for="rating">Rating:</label>-->
        <#--<input type="text" class="form-control" id="rating" name="rating" placeholder="Enter rating"-->
        <#--required="required"-->
        <#--value="${(event.rating)!""}">-->
        <#--</div>-->


            <div class="form-group">
                <label for="ratingGroup">Rating:</label>

                <div class="form-group" id="ratingGroup">
                    <label class="radio-inline">
                        <input ${((event.rating == 'LOW')?then('checked', ''))!''}
                                type="radio" name="rating" value="LOW" required>LOW
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="rating" value="MID"
                               required ${((event.rating == 'MID')?then('checked', ''))!''}>MID
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="rating" value="HIGH"
                               required ${((event.rating == 'HIGH')?then('checked', ''))!''}>HIGH
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label>Air dates:</label>
                <div class="air_date_row">
                    <input type="datetime-local" class="form-control" name="airDates[0]"
                           placeholder="Enter air date"
                           required="required">
                </div>
                <button type="button" class="btn btn-success" id="addOneRow">+</button>
            </div>


            <div class="btn-group">
                <button type="button" class="btn btn-warning" onclick="location.href='../events'">Cancel</button>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
</body>

</html>