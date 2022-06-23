package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	// 글 등록
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
		System.out.println(vo.getSeq());
		System.out.println(vo.getTitle());
		System.out.println(vo.getWriter());
		System.out.println(vo.getContent());
		System.out.println(vo.getRegDate());
		System.out.println(vo.getCnt());
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		model.addAttribute("board", boardDAO.getBoard(vo));		// Model 정보 저장
		return "getBoard.jsp";		// View 이름 리턴
	}

	// 검색 조건 목록 검색
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
//	// 글 목록 검색
//	@RequestMapping("/getBoardList.do")
//	public String getBoardList(@RequestParam(value="searchCondition", 
//				defaultValue="TITLE", required=false) String condition, 
//				@RequestParam(value="searchKeyword", defaultValue="", required=false) 
//				String keyword, BoardDAO boardDAO, Model model) {
//		System.out.println("검색 조건 : " + condition);
//		System.out.println("검색 단어 : " + keyword);
//		// Model 정보 저장
////		model.addAttribute("boardList", boardDAO.getBoardList(vo));
//		return "getBoardList.jsp";		// View 이름 리턴
//	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {		
		// Model ���� ����
		model.addAttribute("boardList", boardDAO.getBoardList(vo));																
		return "getBoardList.jsp"; // View �̸� ����
	}
}