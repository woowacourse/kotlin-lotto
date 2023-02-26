package common

import domain.LottoNumber

fun Set<Int>.convertToLottoNumberSet(): Set<LottoNumber> = this.map { LottoNumber.from(it) }.toSet()
