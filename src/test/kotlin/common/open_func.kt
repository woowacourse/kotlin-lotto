package common

import domain.Lotto
import domain.LottoNumber

fun Set<Int>.convertToLottoNumberSet(): Set<LottoNumber> = this.map { LottoNumber.from(it) }.toSet()
fun Set<Int>.convertToLotto(): Lotto = Lotto(this.map { LottoNumber.from(it) }.toSet())
