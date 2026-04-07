package com.surfit.backend.domain.wiki.client;

import com.surfit.backend.domain.wiki.client.dto.WikiArticleResponse;
import com.surfit.backend.domain.wiki.client.dto.WikiImageInfoResponse;

public interface WikiClient {
	WikiArticleResponse fetchWikiArticle(String title);

	WikiImageInfoResponse fetchImageBatch(String title);
}
