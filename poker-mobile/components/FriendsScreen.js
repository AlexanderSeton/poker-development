import React from "react";
import { View, Text, Button, StyleSheet } from "react-native";

const FriendsScreen = (props) => {
    return(
        <View style={styles.main}>
            <Text style={styles.text}>Friends List</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "7.5%",
    },
    text: {
        textAlign: "center",
        fontSize: 25,
        padding: "3%",
        marginBottom: "5%",
    },
    smallText: {
        textAlign: "center",
        fontSize: 15,
    },
});

export default FriendsScreen;
