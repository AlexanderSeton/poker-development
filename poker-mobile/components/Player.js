import React from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import { backgroundColor, color } from 'react-native/Libraries/Components/View/ReactNativeStyleAttributes';
// import faceDownCard from "../assets/cards/2B.svg";

const Player = function(props) {

    const checkIfUser = function() {
        if (props.player.id == props.userId) {
            return "blue";
        }
        return "black";
    }

    // const checkIfFolded = function() {
    //     if (props.player.folded == true) {
    //         return "darkgrey";
    //     }
    //     return "white";
    // }

    // const checkIfActive = function(props) {
    //     if (props.player["active"] == true) {
    //         return "boldest"
    //     }
    //     return "normal"
    // }

    return(
        <View style={styles.player}>
            <Image
                style ={styles.image}
                source={require("../assets/card_icon.png")}
            />
            <Text style={{ color: checkIfUser() }}>{props.player.username}</Text>
            <Text style={{ color: checkIfUser() }}>£{props.player.stack}</Text>
            {props.player.folded ?
            <Text>Folded</Text>
            : null}
        </View>
    )
}

const styles = StyleSheet.create({
    player: {
    borderWidth: 2,
    borderRadius: 25,
    // backgroundColor: "lightgrey",
    width: "30%",
    height: "100%",
    alignItems: "center",
    textAlign: "center",
    paddingTop: "2%",
    },
    image: {
        width: "40%",
        height: "40%",
        marginBottom: "2.5%",
        marginTop: "2.5%",
    },
});

export default Player;
