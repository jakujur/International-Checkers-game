package com.checkers;

import com.checkers.BoardCompiler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalCaptures {

    private final BoardCompiler board;

    private final Set<PiecePosition> allPossibleCaptures = new HashSet<>();
    private final Set<PiecePosition> allPiecesWhichCanCapture = new HashSet<>();
    private final Set<PiecePosition> positionsAfterCapturing = new HashSet<>();

    public NormalCaptures(BoardCompiler board) {
        this.board = board;
    }

    public void calculateAllPossibleCaptures(PieceType.Color color) {
        allPossibleCaptures.clear();
        allPiecesWhichCanCapture.clear();

        for (Map.Entry<PiecePosition, PieceType> piece : board.getBoard().entrySet()) {
            if(piece.getValue().getPieceColor() == color && piece.getValue().getPieceType().isNormal()){

                if(capturePossibility(piece.getKey(), 1, 1)){
                    allPossibleCaptures.add(new PiecePosition(piece.getKey().getCol() + 1, piece.getKey().getRow() + 1));
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
                if(capturePossibility(piece.getKey(), -1, -1)){
                    allPossibleCaptures.add(new PiecePosition(piece.getKey().getCol() - 1, piece.getKey().getRow() - 1));
                    allPiecesWhichCanCapture.add(piece.getKey());
                }

                if(capturePossibility(piece.getKey(), -1, 1)){
                    allPossibleCaptures.add(new PiecePosition(piece.getKey().getCol() - 1, piece.getKey().getRow() + 1));
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
                if(capturePossibility(piece.getKey(), 1, -1)){
                    allPossibleCaptures.add(new PiecePosition(piece.getKey().getCol() + 1, piece.getKey().getRow() - 1));
                    allPiecesWhichCanCapture.add(piece.getKey());
                }
            }
        }
    }

    public void captureCalculator(PiecePosition position) {
        positionsAfterCapturing.clear();
        allPossibleCaptures.clear();

        if(capturePossibility(position, 1, 1)) {
            allPossibleCaptures.add(new PiecePosition(position.getCol() + 1, position.getRow() + 1));
            positionsAfterCapturing.add(new PiecePosition(position.getCol() + 2, position.getRow() + 2));
        }

        if(capturePossibility(position, - 1, - 1)) {
            allPossibleCaptures.add(new PiecePosition(position.getCol() - 1, position.getRow() - 1));
            positionsAfterCapturing.add(new PiecePosition(position.getCol() - 2, position.getRow() - 2));
        }

        if(capturePossibility(position, 1, - 1)) {
            allPossibleCaptures.add(new PiecePosition(position.getCol() + 1, position.getRow() - 1));
            positionsAfterCapturing.add(new PiecePosition(position.getCol() + 2, position.getRow() - 2));
        }

        if(capturePossibility(position, - 1, 1)) {
            allPossibleCaptures.add(new PiecePosition(position.getCol() - 1, position.getRow() + 1));
            positionsAfterCapturing.add(new PiecePosition(position.getCol() - 2, position.getRow() + 2));
        }

    }

    private boolean capturePossibility(PiecePosition actualPosition, int col, int row) {
        return new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row).isValidPosition()
                && new PiecePosition(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)).isValidPosition()
                && !board.isFieldNull(new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row))
                && board.isFieldNull(new PiecePosition(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)))
                && (board.getPiece(actualPosition).getPieceColor() != board.getPiece(new PiecePosition(actualPosition.getCol() + col, actualPosition.getRow() + row)).getPieceColor());
    }

    public void clear() {
        allPossibleCaptures.clear();
        allPiecesWhichCanCapture.clear();
        positionsAfterCapturing.clear();
    }

    public Set<PiecePosition> getAllPossibleCaptures() {
        return allPossibleCaptures;
    }

    public Set<PiecePosition> getAllPiecesWhichCanCapture() {
        return allPiecesWhichCanCapture;
    }

    public Set<PiecePosition> getPositionsAfterCapturing() {
        return positionsAfterCapturing;
    }

}