package com.lec.spring.controller;

import com.lec.spring.domain.Board;
import com.lec.spring.domain.BoardType;
import com.lec.spring.domain.Club;
import com.lec.spring.domain.Comment;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.ClubService;
import com.lec.spring.service.CommentService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import jakarta.validation.Valid;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // 기본생성자
    public BoardController() {
    }

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Board board,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", board.getUser());
            redirectAttributes.addFlashAttribute("title", board.getTitle());
            redirectAttributes.addFlashAttribute("content", board.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_" + err.getField(), err.getCode());
            }
            return "redirect:/board/write";
        }
        model.addAttribute("result", boardService.write(board, files));

        return "board/writeOk";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
//        model.addAttribute("board", boardService.detail(id));
//        model.addAttribute("club", clubService.getClubById(id));

        Board board = boardService.detail(id);

        model.addAttribute("board", board);
        System.out.println("board : " +board.toString());

        Club club = board.getClub() != null ? clubService.getClubById(board.getClub().getId()) : null;
        model.addAttribute("club", club);

        List<Comment> comments = commentService.list(id).getList();  // 게시물의 댓글 목록을 가져옴
        int cnt = commentService.list(id).getCount();

        model.addAttribute("cnt", cnt);
        model.addAttribute("comments", comments);

        System.out.println("comments 갯수: " + comments.size());
        System.out.println("comments 정보 : " + comments.toString());

        return "board/detail";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page, Model model){
        boardService.list(page, model);

        List<Board> boards = boardService.list();
//        List<Board> boards = (List<Board>) model.getAttribute("list");
        List<Club> clubs = clubService.getAllClubs();

        // 디버깅 추가
//        for (Board board : boards) {
//            System.out.println("Board.toString : " + board.toString() + "\n");
//        }

        model.addAttribute("boards", boards);
        model.addAttribute("clubs", clubs);

        return "board/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("board", boardService.findById(id));
        return "board/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Board board,
            BindingResult result,
            Long [] delfile,
            Model model,
            RedirectAttributes redirectAttrs
    ){
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("title", board.getTitle());
            redirectAttrs.addFlashAttribute("content", board.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }
            return "redirect:/common/board_update" + board.getId();
        }
        model.addAttribute("result", boardService.update(board, files, delfile));

        return "board/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Long id, Model model){
        model.addAttribute("result", boardService.deleteById(id));

        return "board/deleteOk";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.setValidator(new BoardValidator());
//    }

    // 페이징 관련
    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);

        return "redirect:/board/list?page=" + page;
    }

}
