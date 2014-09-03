package com.shop.daoInf;

import java.util.List;

import com.shop.model.Indent;
import com.shop.model.User;

public interface IndentDaoInf {
			void deleteIndentById(int id);
			void saveIndent(Indent indent);
			List<Indent> findAddIndents(Indent indent,int first,int max);
			int getSize(Indent indent);
			List<Indent> findAddIndents();
			List<Indent> findAddIndents(User user);
			/**
			 * �޸Ķ������ѷ�����״̬�ı�
			 * @param indentId
			 */
			void changeIndentForUser(int indentId);
			void changeIndentForAdmin(int indentId);
			List<Indent> findIndentByUsername(String username);
			void onePlus(int id);
			List<Indent>  findAllIndentsByUser(User user ,int first,int max);
			Indent findIndentById(int id);
}
