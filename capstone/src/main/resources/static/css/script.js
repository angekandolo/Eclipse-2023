   /* $(document).ready(function() {
      // Collapse the sidebar and remove "active" class from toggle button
      $('#sidebar').removeClass('active');
      $('#sidebarCollapse').removeClass('active');

      // Toggle sidebar and toggle button on click
      $('#sidebarCollapse').on('click', function() {
        $('#sidebar').toggleClass('active');
        $(this).toggleClass('active');
      });

      // Handle dropdown toggle
      $('.dropdown-toggle').on('click', function(e) {
        e.preventDefault();
        $(this).next('.dropdown-menu').toggleClass('active');
      });

      // Close dropdown when clicking outside
      $(document).on('click', function(e) {
        if (!$(e.target).closest('.dropdown').length) {
          $('.dropdown-menu').removeClass('active');
        }
      });
    });*/
     $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
                // Add click event handler for dropdown toggle
    $('.dropdown-toggle').on('click', function (e) {
        e.stopPropagation();
        $(this).next('.collapse').toggleClass('show');

        // Add a class to the dropdown toggle to indicate it is open
        $(this).toggleClass('dropdown-open');
    });

    // Prevent the dropdown from closing when clicking inside it
    $('.collapse').on('click', function (e) {
        e.stopPropagation();
    });

    // Close the dropdown when clicking outside of it
    $(document).on('click', function () {
        $('.collapse').removeClass('show');
        $('.dropdown-toggle').removeClass('dropdown-open');
    });
});

        
        
    /*        function toggleReplyForm(replyFormId) {
        var replyForm = document.getElementById(replyFormId);
        if (replyForm.style.display === 'none') {
            replyForm.style.display = 'block';
        } else {
            replyForm.style.display = 'none';
        }
    }*/
        
/*function favoriteAnimePage(buttonElement) {
  const animeName = buttonElement.dataset.animeName;
  const data = { name: animeName };
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        // Request was successful, handle the response data here
        console.log(xhr.responseText);
      } else {
        // Request failed, handle errors here
        console.error('Error favoriting anime:', xhr.statusText);
      }
    }
  };

  xhr.open('POST', '/favorite/add');
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(data));
}*/
    
    
    