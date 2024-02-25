package domain.model

import domain.Rank

data class LottoDrawingResult(val statistics: Map<Rank, Int>)
