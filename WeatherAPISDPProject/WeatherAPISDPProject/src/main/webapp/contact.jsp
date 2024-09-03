<!doctype html>
<html lang="en">
  <head>
    <title>WEATHER EXPLORER</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
  <body>
 
<%@ include file="usernavbar.jsp" %>

      <div class="container">
   
        <h1>Contact Us</h1>
        <form action=insertcontact method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required>

            <label for="message">Message:</label>
            <textarea id="message" name="message" rows="6" required></textarea>

            <button type="submit">Submit</button>
        </form>
    </div>
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

  </body>
</html>






















