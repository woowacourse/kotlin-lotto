package controller

import domain.LottoGenerator
import domain.ProfitCalculator
import domain.validator.NumericValidator

class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val lottoGenerator: LottoGenerator = LottoGenerator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator(),
) {

}
