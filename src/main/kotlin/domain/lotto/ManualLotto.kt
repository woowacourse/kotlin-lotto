package domain.lotto

import domain.lotto.number.LottoNumber

class ManualLotto(lottoNumbers: Set<LottoNumber>) : PurchasedLotto(lottoNumbers)
