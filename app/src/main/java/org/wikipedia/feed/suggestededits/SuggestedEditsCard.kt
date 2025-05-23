package org.wikipedia.feed.suggestededits

import org.wikipedia.R
import org.wikipedia.dataclient.WikiSite
import org.wikipedia.descriptions.DescriptionEditActivity
import org.wikipedia.feed.model.CardType
import org.wikipedia.feed.model.WikiSiteCard
import org.wikipedia.util.DateUtil
import org.wikipedia.util.L10nUtil

class SuggestedEditsCard(val cardTypes: List<DescriptionEditActivity.Action>,
                         wiki: WikiSite,
                         val age: Int) : WikiSiteCard(wiki) {

    override fun type(): CardType {
        return CardType.SUGGESTED_EDITS
    }

    override fun title(): String {
        return L10nUtil.getString(wikiSite().languageCode, R.string.suggested_edits_feed_card_title)
    }

    override fun subtitle(): String {
        return DateUtil.getFeedCardDateString(age)
    }

    fun footerActionText(): String {
        return L10nUtil.getString(wikiSite().languageCode, R.string.suggested_card_more_edits)
    }
}
