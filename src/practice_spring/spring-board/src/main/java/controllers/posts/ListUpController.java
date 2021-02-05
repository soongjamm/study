package controllers.posts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.posts.ListUpPostsService;

@Controller
@RequestMapping("/posts")
public class ListUpController {

    private ListUpPostsService listUpPostsService;

    @GetMapping
    public void listUp() {
    }
}
