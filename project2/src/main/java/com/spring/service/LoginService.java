package com.spring.service;

import com.spring.domain.UserDTO;
import com.spring.util.PageMaker;

public interface LoginService {
	//Board test
//	public List<BoardEntity> selectAllBoard();
//	public BoardEntity selectOneBoard(int id);
//	public void updateOne(BoardDTO dto);
//	public void insertOne(BoardDTO boardDTO);
//	public void deleteOne(BoardDTO dto);
//	public String removeSelectList(List<Integer> seqList);
//	
//	public List<BoardEntity> findMovieByPaging(PageMaker pageMaker);
//	
//	public void insertBoard(String title, String content);
	
	//project
	public UserDTO findUsernameAndPassword(UserDTO user);
	
	public int registerUser(UserDTO user);

	public boolean checkUsername(String username);

}
