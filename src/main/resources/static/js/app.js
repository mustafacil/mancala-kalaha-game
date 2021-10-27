let boardId;

function startGame() {
    jQuery.ajax({
        type: "POST",
        url: "/api/start",
        dataType: 'json',
        success: function (res) {
            console.log(res);
            boardId = res.id;
            updateBoard(res);
        },
        error: function (errorThrown) {
            alert("An error occurred, " + errorThrown);
        }
    });

}

function makeAMove(pitIndex) {
    jQuery.ajax({
        type: "GET",
        url: "/api/makeamove/" + pitIndex + "/boardid/" + boardId,
        dataType: 'json',
        success: function (res) {
            console.log(res);
            updateBoard(res);
            checkGameIsFinished(res);
        },
        error: function (errorThrown) {
            alert("An error occurred, " + errorThrown);
        }
    });

}

function updateBoard(res){
    document.getElementById("player1-info").innerHTML = "Player 1 : " + res.game.playerList[0].username;
    document.getElementById("player2-info").innerHTML = "Player 2 : " + res.game.playerList[1].username;
    let nextPlayerUsername = res.game.playerList[res.game.nextPlayerIndex].username;
    document.getElementById("game-info-message").innerHTML = res.game.gameStateInfo +". It is " + nextPlayerUsername +"'s Turn.";

    for(let i=0; i<14; i++){
        let pitId="pit-"+i;
        document.getElementById(pitId).innerHTML = res.pits[i].numberOfStones;
    }
    disableButtonsByPlayerIndex(res.game.nextPlayerIndex);

}

function disableButtonsByPlayerIndex(playerIndex){

    if(playerIndex === 0){
        for(let i=7; i<=12; i++){
            let toHiddenId = "make-a-move-" + i;
            document.getElementById(toHiddenId).style.visibility = "hidden";

            let toVisibleId= "make-a-move-" + (i-7);
            document.getElementById(toVisibleId).style.visibility = "visible";
        }

    }else if(playerIndex === 1){
        for(let i=0; i<=5; i++){
            let toHiddenId = "make-a-move-" + i;
            document.getElementById(toHiddenId).style.visibility = "hidden";

            let toVisibleId= "make-a-move-" + (i+7);
            document.getElementById(toVisibleId).style.visibility = "visible";
        }

    }
}


function checkGameIsFinished(res){

    if(res.game.gameStateInfo === "The Game Is Finished"){
        determineTheWinner(res);
    }

}

function determineTheWinner(res){
    let numberOfStonesOfPlayer1 = 0;

    for(let i=0; i<7; i++){
        numberOfStonesOfPlayer1 += res.pits[i].numberOfStones;
    }

    let numberOfStonesOfPlayer2 = 0;
    for(let i=7; i<14; i++){
        numberOfStonesOfPlayer2 += res.pits[i].numberOfStones;
    }

    let player1 = res.game.playerList[0];
    let player2 = res.game.playerList[1];
    let resultMessage;
    if(numberOfStonesOfPlayer1 > numberOfStonesOfPlayer2){
        resultMessage = res.game.gameStateInfo + " Winner is: " + player1.username + " Player1 Stones: " + numberOfStonesOfPlayer1 + " Player2 Stones: " + numberOfStonesOfPlayer2;
        document.getElementById("game-info-message").innerHTML = resultMessage;
    }else if(numberOfStonesOfPlayer2 > numberOfStonesOfPlayer2){
        resultMessage = res.game.gameStateInfo + " Winner is: " + player2.username + " Player1 Stones: " + numberOfStonesOfPlayer1 + " Player2 Stones: " + numberOfStonesOfPlayer2;
        document.getElementById("game-info-message").innerHTML = resultMessage;
    }
}



