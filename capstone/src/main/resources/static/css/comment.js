



/*function displayComments(comments) {
  const commentsSection = document.getElementById("comments-section");
  commentsSection.innerHTML = "";

  comments.forEach((comment) => {
    // Create a div for each comment
    const commentDiv = document.createElement("div");
    commentDiv.classList.add("comment");

    // Display the comment's user name and text
    const commentUser = document.createElement("p");
    commentUser.innerText = "User: " + comment.user.name;
    const commentText = document.createElement("p");
    commentText.innerText = "Comment: " + comment.commentText;

    // Create a reply button for the comment
    const replyButton = document.createElement("button");
    replyButton.innerText = "Reply";
    replyButton.addEventListener("click", () => toggleReplyForm(comment.id));

    // Create a div for the reply form and set it initially hidden
    const replyFormDiv = document.createElement("div");
    replyFormDiv.id = `reply-form-${comment.id}`;
    replyFormDiv.classList.add("reply-form");
    replyFormDiv.style.display = "none";

    // The reply form fields (user name and reply text)
    const replyUserName = document.createElement("input");
    replyUserName.type = "text";
    replyUserName.placeholder = "Your Name";
    const replyText = document.createElement("textarea");
    replyText.placeholder = "Your Reply";
    const submitReplyButton = document.createElement("button");
    submitReplyButton.innerText = "Submit Reply";
    submitReplyButton.addEventListener("click", () => submitReply(comment.id));

    // Append all elements to the comment div
    commentDiv.appendChild(commentUser);
    commentDiv.appendChild(commentText);
    commentDiv.appendChild(replyButton);
    commentDiv.appendChild(replyFormDiv);

    replyFormDiv.appendChild(replyUserName);
    replyFormDiv.appendChild(replyText);
    replyFormDiv.appendChild(submitReplyButton);

    // Append the comment div to the comments section
    commentsSection.appendChild(commentDiv);
  });
}

// Function to toggle the reply form visibility
function toggleReplyForm(commentId) {
  const replyFormDiv = document.getElementById(`reply-form-${commentId}`);
  if (replyFormDiv.style.display === "none") {
    replyFormDiv.style.display = "block";
  } else {
    replyFormDiv.style.display = "none";
  }
}

// Function to submit a reply
// Function to submit a reply
function submitReplyForm(commentId) {
  const replyUserName = document.getElementById("user-name").value;
  const replyText = document.getElementById("comment-text").value;

  // Call the submitReply function here to send the reply data to the server
  const reply = {
    userName: replyUserName,
    replyText: replyText,
    comment: {
      id: commentId,
    },
  };

  fetch("/replies", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(reply),
  })
    .then((response) => response.json())
    .then((submittedReply) => {
      console.log("Reply submitted successfully:", submittedReply);
      // Refresh the comments section after successful submission
      fetchComments(pageId);
    })
    .catch((error) => {
      console.error("Error submitting reply:", error);
    });
}

// Function to handle comment submission
function submitComment(pageId, userName, commentText) {
  console.log("Submitting comment...");

  const comment = {
    pageId: pageId,
    userName: userName,
    commentText: commentText,
  };

  fetch("/comments", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(comment),
  })
    .then((response) => response.json())
    .then((submittedComment) => {
      console.log("Comment submitted successfully:", submittedComment);
      // Refresh the comments section after successful submission
      fetchComments(pageId);
    })
    .catch((error) => {
      console.error("Error submitting comment:", error);
    });
}


function fetchComments(pageId) {
  fetch(`/comments/${pageId}`)
    .then((response) => response.json())
    .then((data) => {
	  console.log("Response data:", data);
      const comments = data.data; // Extract the comments array from the response
      displayComments(comments);
    })
    .catch((error) => {
      console.error("Error fetching comments:", error);
    });
}
 function handleSubmit(event) {
  event.preventDefault();
  const pageId = document.getElementById("page").dataset.pageId; // Assuming the "page" element contains the pageId in its dataset
  const userName = document.getElementById("user-name").value;
  const commentText = document.getElementById("comment-text").value;
  submitComment(pageId, userName, commentText);

  const comment = {
    pageId: pageId,
    userName: userName,
    commentText: commentText,
  };

  fetch("/comments", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(comment),
  })
    .then((response) => {
      if (response.ok) {
        return response.json(); // Parse the response as JSON if it's successful
      } else {
        throw new Error("Error submitting comment");
      }
    })
    .then((savedComment) => {
      console.log("Comment submitted successfully:", savedComment);
      // Refresh the comments section after successful submission
      fetchComments(pageId);
    })
    .catch((error) => {
      console.error("Error submitting comment:", error.message);
    });
}

// Call fetchComments with the appropriate page ID when the page loads
window.addEventListener("load", function () {
	const pageId = document.getElementById("page").dataset.pageId;
  //const pageId = "your_page_id"; // Replace with the actual page ID for each HTML page
  fetchComments(pageId);
  

// Add an event listener to the form submit event
const commentForm = document.getElementById("comment-form");
commentForm.addEventListener("submit", handleSubmit);
});*/









  function handleSubmit(event) {
            event.preventDefault();
            const pageId = document.getElementById("page").dataset.pageId;
            const userName = document.getElementById("user-name").value;
            const commentText = document.getElementById("comment-text").value;

            // Create a JavaScript object to hold the form data
            const formData = {
                pageId: pageId,
                userName: userName,
                commentText: commentText
            };

            // Send the form data to the server using fetch
            fetch(`/comments/${pageId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error submitting comment.");
                    }
                    return response.json();
                })
                .then(data => {
                    // If the response is successful, you can do something with the returned data
                    console.log("Comment saved successfully: ", data);
                    // For example, update the comments section to display the newly submitted comment
                    const commentsSection = document.getElementById("comments-section");
                    const newComment = document.createElement("div");
                    newComment.textContent = `User: ${data.user.username}, Comment: ${data.commentText}`;
                    commentsSection.appendChild(newComment);
                })
                .catch(error => {
                    // If there's an error, handle it here
                    console.error("Error submitting comment: ", error);
                });
        }

        // Add event listener to the form for form submission
        const commentForm = document.getElementById("comment-form");
        commentForm.addEventListener("submit", handleSubmit);














