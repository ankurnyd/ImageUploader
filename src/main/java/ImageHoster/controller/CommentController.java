package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller // Annotation added
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title,
                             @RequestParam("comment") String text, HttpSession session, Model model){

        Image image = imageService.getImage(imageId);
        User loggedInUser = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment(); // Initializing comment object so all data can be stored
        comment.setImage(image);
        comment.setUser(loggedInUser);
        comment.setText(text);
        comment.setCreatedDate(LocalDate.now());  // Sending local date as per the requirement
        commentService.createComment(comment);
        return "redirect:/images/" + imageId + "/" + title;
    }


}
