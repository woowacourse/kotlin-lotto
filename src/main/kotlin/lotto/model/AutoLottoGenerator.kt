package lotto.model

import lotto.contants.LottoRuleConstants

class AutoLottoGenerator : LottoGenerator {
    override fun generateLotto(): LottoTicket {
        val lottoNumbers = (LottoRuleConstants.MINIMUM_NUMBER.value..LottoRuleConstants.MAXIMUM_NUMBER.value)
            .shuffled()
            .take(LottoRuleConstants.LOTTO_PICK_COUNT.value)
            .sorted()
            .map(::LottoNumber)
        return LottoTicket(lottoNumbers)
    }
}