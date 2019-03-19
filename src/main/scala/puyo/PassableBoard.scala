package puyo

case class PassableBoard(blobs: List[PassableBlob], drawCurrent: Boolean,
    p1: PassableBlob, p2: PassableBlob)