import React, { useEffect, useState } from "react";
import { View, Text, Button, TextInput, StyleSheet, SafeAreaView, Alert } from "react-native";
import Player from "../components/Player"
import SockJS from "sockjs-client";
import { Stomp } from "stomp-websocket/lib/stomp";
import HoleCard from "../components/HoleCard";
import UserData from "../components/UserData";


let stompClient;

const GameScreen = (props) => {

    // WEBSOCKET ROUTES:
    // gameData - refreshed after every player"s action/move
    // allPlayers - refreshed after every player"s action/move
    // holCards - refreshed start of every round/game
    // ?winner - refreshed end of every round/game

    // CLIENT-SIDE STATES (individual to each user)
    const [userId, setUserId] = useState(props.route.params.userId);
    const [betAmount, setBetAmount] = useState();

    // SERVER-SIDE STATES
    // individual user states
    const [holeCards, setHoleCards] = useState([]); // needs own route (just get user"s cards by id)
    const [user, setUser] = useState(); // calculated from allPlayers
    const [userContribution, setUserContribution] = useState();
    const [players, setPlayers] = useState([]); // calculated from allPlayers

    // constant states (same for every player)
    const [communityCards, setCommunityCards] = useState(); // gameDataRoute (send after every player"s action)
    const [pot, setPot] = useState(); // gameDataRoute
    const [smallBlind, setSmallBlind] = useState(); // gameDataRoute
    const [bigBlind, setBigBlind] = useState(); // gameDataRoute
    const [activePlayer, setActivePlayer] = useState(); // calculated from allPlayers
    const [winner, setWinner] = useState(); // standalone route ??
    const [largestContribution, setLargestContribution] = useState();
    const [gameKey, setGameKey] = useState();
    const [dealt, setDealt] = useState(false);

    useEffect(async() => {
        await connect();
        setTimeout(() => {
            if (props.route.params.newGame !== undefined) {
                console.log("creating new game");
                createNewGame();
            } else {
                connectToGame();
                console.log("connecting to game");
            }
        }, 1000)
    }, [])

    // useEffect(async() => {
    //     const response = await fetch(`http://localhost:8080/players/${props.route.params.userId}`);
    //     console.log("RESPONSE");
    //     console.log(response);
    // }, [])

    // WEBSOCKET FUNCTIONS
    async function connect() {
        let socket = new SockJS("http://localhost:8080/ws");
        stompClient = await Stomp.over(socket);
        await stompClient.connect({}, function (frame) {
            // setConnected(true);
            console.log("Connected: " + frame);

            stompClient.subscribe("/client/greetings", async function(response) {
                console.log("client/greetings:"); // test
                console.log(JSON.parse(response["body"])); // test
                let data = await JSON.parse(response["body"]);
                // check successfully joined
                if (data["statusCode"] == "OK") {
                    console.log("success") // test
                    let players = await data["body"]["players"];
                    setPlayers(players);
                    let board = await data["body"]["board"];
                    setCommunityCards(board);
                    for (let i=0; i<players.length; i++) {
                        if (players[i]["id"] == props.route.params.userId) {
                            let user = await players[i];
                            setUser(user);
                        }
                    }
                    let pot = await data["pot"];
                    setPot(pot);
                    let smallBlind = await data["smallBlind"];
                    setSmallBlind(smallBlind);
                    let bigBlind = await smallBlind * 2;
                    setBigBlind(bigBlind);
                }
                // handle unsuccessful create game
                else {
                    Alert.alert(
                        "Game key already in use",
                        "Enter a new game key, then press submit",
                        [
                            {
                                text: "Cancel",
                                style: "cancel"
                            },
                            { 
                                text: "OK",
                            }
                        ]
                    );
                    // return user to the create game page
                    await props.navigation.navigate("Create Game");
                }
            });

            stompClient.subscribe("/client/join", async function(response) {
                console.log("client/join:"); // test
                console.log(JSON.parse(response["body"])); // test
                let data = await JSON.parse(response["body"]);
                console.log("DATA: " + data);
                // check successfully joined
                if (data["statusCode"] == "OK") {
                    console.log("success") // test
                    console.log(data);
                    let players = await data["body"]["players"];
                    setPlayers(players);
                    let board = await data["body"]["board"];
                    setCommunityCards(board);
                    for (let i=0; i<players.length; i++) {
                        if (players[i]["id"] == props.route.params.userId) {
                            let user = await players[i];
                            setUser(user);
                        }
                    }
                    let pot = await data["pot"];
                    setPot(pot);
                    let smallBlind = await data["smallBlind"];
                    setSmallBlind(smallBlind);
                    let bigBlind = await smallBlind * 2;
                    setBigBlind(bigBlind);
                }
                // handle if game not found
                else {
                    Alert.alert(
                        "Game key not found",
                        "Enter a new game key, then press submit",
                        [
                            {
                                text: "Cancel",
                                style: "cancel"
                            },
                            { 
                                text: "OK",
                            }
                        ]
                    );
                    // return user to the create game page
                    props.navigation.navigate("Join Game");
                }
            });
            stompClient.subscribe("/client/deal", async function(response) {
                console.log("client/deal:"); // test
                console.log(JSON.parse(response["body"])); // test
                let data = await JSON.parse(response["body"]);
                // check response successful
                if (data["statusCodeValue"] == 200) {
                    console.log("success");
                    let playersHands = await data["body"];
                    for (const [key, value] of Object.entries(playersHands)) {
                        if (key.toString().toLowerCase() == props.route.params.userId) {
                            const valueFormatted = [];
                            for (let i=0; i<value.length; i+=2) {
                                let formatStringValue = value[i] + " " + value[i+1];
                                valueFormatted.push(formatStringValue);
                            }
                            setHoleCards(valueFormatted);
                            console.log("set hole cards");
                            console.log(valueFormatted);
                        }
                    }    
                }
            });
        });
    }
    

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        // setConnected(false);
        console.log("Disconnected");
    }

    function createNewGame() {
        stompClient.send(`/server/create/game/${props.route.params.gameKey}`, {}, JSON.stringify(
            {
                "id": props.route.params.userId,
                "bigBlindValue": props.route.params.bigBlind
            }));
    }

    function connectToGame(){
        console.log(`Game Key: ${props.route.params.gameKey}. User ID: ${props.route.params.userId}`)
        stompClient.send(`/server/join/game/${props.route.params.gameKey}`, {}, JSON.stringify(
            {
                "id": props.route.params.userId
            }
        ))
    }

    function handleFold() {
        // connect();
        stompClient.send(`/server/action/game/${props.route.params.gameKey}`, {}, JSON.stringify({
            "action": "fold",
            "betAmount": 0,
            "playerId": userId
        }));
    }

    function handleBet(){
        let amount = userContribution + betAmount;
        setUserContribution(amount);
        stompClient.send(`/server/action/game/${props.route.params.gameKey}`, {}, JSON.stringify({
            "action": "bet",
            "betAmount": betAmount,
            "playerId": props.route.params.userId
        }));
    }

    function handleCall(event){
        let amount = userContribution + betAmount;
        setUserContribution(amount);
        stompClient.send(`/server/action/game/${props.route.params.gameKey}`, {}, JSON.stringify({
            "action": "call",
            "betAmount": event.target.value,
            "playerId": props.route.params.userId
        }));
    }    
    
    function handleDealHoleCards(){
        setDealt(true);
        stompClient.send(`/server/action/game/${props.route.params.gameKey}/deal`, {}, JSON.stringify({
            "action": "deal",
            "betAmount": betAmount,
            "playerId": props.route.params.userId,
            "gameId": props.route.params.gameId
        }));
    }

    const playerItems = players.map((player, index) => {
        return <Player player={player} userId={props.route.params.userId} key={index} />
    });

    const holeCardItems = holeCards.map((holeCard, index) => {
        let data = holeCard.split(" ");
        let suit = data[0];
        let value = data[1];
        let path = suit + value;
        // let fullPath = `../assets/cards/${path}.png`;
        return <HoleCard card={holeCard} path={path} key={index} />
    });
       
    return(
        <SafeAreaView>

            <Text style={styles.gameKey}>Game Key: {props.route.params.gameKey}</Text>

            <View style={styles.main}>

                <View style={styles.top}>

                    <View style={styles.playerView}>
                        {players.length != 0 ?
                            playerItems 
                        : null}
                    </View>

                    <View style={styles.board}>

                        {!dealt? 
                            <View style={styles.dealButtonView}>
                                <Button 
                                    title="Deal"
                                    style={styles.dealButton}
                                    onPress={() => 
                                        handleDealHoleCards()
                                    } 
                                />
                            </View>
                            : null }
                        

                    </View> 
                    
                    <View style={styles.playerView}>

                    </View>

                </View>

                <View style={styles.bottom}>
                    <View style={styles.userTop}>
                        <View style={styles.userHand}>
                            <Text style={styles.text}>Hole Cards</Text>
                            <View style={styles.holeCards}>
                                {holeCardItems}
                            </View>
                        </View>
                        <View style={styles.userData}>
                            <Text style={styles.text}>Stats</Text>
                            
                            {user != undefined ?
                            // <View style={styles.userData}>
                                <UserData user={user} />
                            // </View>
                            : null}
                            
                        </View>
                    </View>

                    <View style={styles.userBottom}>
                        <View style={styles.buttonView}>
                            <Button
                                title="Fold"
                                onPress={() => 
                                    handleFold()
                                }
                            />
                        </View>
                        <View style={styles.buttonView}>
                            <Button
                                title="Call/Check"
                                value={largestContribution - userContribution}
                                onPress={() => 
                                    handleCall()
                                }
                            />
                        </View>
                        <View style={styles.buttonView}>
                            <Button
                                title="Bet"
                                onPress={() => 
                                    handleBet()
                                }
                            />
                        </View>
                        <TextInput
                            style={styles.input}
                            onChangeText={setBetAmount}
                            value={betAmount}
                            placeholder="Bet Amount..."
                        />
                    </View>
                </View>

            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "3%",
    },
    gameKey: {
        paddingTop: "3%",
        textAlign: "center",
        alignContent: "center",
        fontSize: 20,
    },
    buttonView: {
        height: "60%",
        borderWidth: 1,
        padding: "2%",
        marginLeft: "auto",
        marginRight: "auto",
        borderRadius: 10,
        borderColor: "blue",
        justifyContent: "center",
    },
    dealButtonView: {
        height: "30%",
        width: "40%",
        borderWidth: 1,
        padding: "1%",
        marginTop: "10%",
        marginLeft: "auto",
        marginRight: "auto",
        borderRadius: 10,
        borderColor: "blue",
        justifyContent: "center",
        backgroundColor: "lightgreen",
    },
    holeCards: {
        display: "flex",
        flexDirection: "row",
        // width: "10%",
    },
    input: {
        height: 40,
        margin: 5,
        borderWidth: 1,
        borderRadius: 10,
        padding: 10,
    },
    top: {
        borderWidth: 1,
        height: "50%",
        backgroundColor: "green",
    },
    bottom: {
        borderWidth: 1,
        height: "50%",
    },
    text: {
        textAlign: "center",
        fontSize: 20,
    },
    smallText: {
        textAlign: "center",
        fontSize: 15,
    },
    userTop: {
        height: "55%",
        flexDirection: "row",
    },
    userBottom: {
        height: "45%",
        flexDirection: "row",
        // alignContent: "center",
        justifyContent: "center",
        alignItems: "center",
        paddingBottom: "10%",
    },
    userHand: {
        borderWidth: 1,
        // borderColor: "green",
        width: "60%",
    },
    userData: {
        borderWidth: 1,
        // borderColor: "red",
        width: "40%",
        textAlign: "center",
    },
    board: {
        height: "40%",
        flexDirection: "row",
        justifyContent: "flex-start",
        // backgroundColor: "green",
    },
    playerView: {
        height: "30%",
        // backgroundColor: "green",
        flexDirection: "row",
        justifyContent: "space-between",
        borderWidth: 1,
    },
});

export default GameScreen;
