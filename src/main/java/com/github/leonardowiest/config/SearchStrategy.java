package com.github.leonardowiest.config;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.search.SearchTerm;

import org.springframework.integration.mail.SearchTermStrategy;

/**
 * 
 * @author Leonardo Wiest
 *
 */
public class SearchStrategy implements SearchTermStrategy {

	@Override
	public SearchTerm generateSearchTerm(Flags supportedFlags, Folder folder) {

		return new AcceptAllSearchTerm();
	}

	private class AcceptAllSearchTerm extends SearchTerm {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean match(Message msg) {

			return true;
		}

	}

}
