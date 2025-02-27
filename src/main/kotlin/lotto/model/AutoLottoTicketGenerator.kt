package lotto.model

import lotto.contants.LottoRuleConstants

class AutoLottoTicketGenerator: LottoTicketGenerator {
    override val type: LottoIssueType = LottoIssueType.AUTO

    override fun generateLottoTicket(): LottoTicket {
        val lottoNumbers = (LottoRuleConstants.MINIMUM_NUMBER.value..LottoRuleConstants.MAXIMUM_NUMBER.value)
            .shuffled()
            .take(LottoRuleConstants.LOTTO_PICK_COUNT.value)
            .sorted()
            .map(::LottoNumber)
        return LottoTicket(LottoIssueType.AUTO, lottoNumbers)
    }
}