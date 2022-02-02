import React, { useEffect, useState } from "react";
import { View, Text, Button, StyleSheet } from "react-native";
import { borderColor } from "react-native/Libraries/Components/View/ReactNativeStyleAttributes";

const HomeScreen = (props) => {

    const [loggedIn, setLoggedIn] = useState(false);
    const [userId, setUserId] = useState();

    useEffect(() => {
        if (props.route.params !== undefined) {
            let loggedInStatus = props.route.params.loggedIn;
            let loginId = props.route.params.id;
            setLoggedIn(loggedInStatus);
            setUserId(loginId);
            console.log(loginId);
        }
    })

    return(
        <View style={styles.main}>
            <View style={styles.buttonViewLoggedIn}>
                <Button
                    title="Login"
                    onPress={() =>
                        props.navigation.navigate("Login", {
                            loggedIn: loggedIn
                        })
                    }
                />
            </View>
            <View style={loggedIn ? styles.buttonViewLoggedIn : styles.buttonView}>
                <Button
                    title="Join Game"
                    disabled={!loggedIn}
                    onPress={() =>
                        props.navigation.navigate("Join Game", {
                            "userId": userId
                        })
                    }
                />
            </View>
            <View style={loggedIn ? styles.buttonViewLoggedIn : styles.buttonView}>
                <Button
                    title="Create Game"
                    disabled={!loggedIn}
                    onPress={() =>
                        props.navigation.navigate("Create Game", {
                            "userId": userId
                        })
                    }
                />
            </View>
            <View style={loggedIn ? styles.buttonViewLoggedIn : styles.buttonView}>
                <Button
                    title="Friends"
                    disabled={!loggedIn}
                    onPress={() =>
                        props.navigation.navigate("Friends")
                    }
                />
            </View>
            <View style={loggedIn ? styles.buttonViewLoggedIn : styles.buttonView}>
                <Button
                    title="Account"
                    disabled={!loggedIn}
                    onPress={() =>
                        props.navigation.navigate("Account", {
                            "userId": userId
                        })
                    }
                />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "7.5%",
    },
    buttonView: {
        width: 200,
        borderWidth: 0.5,
        marginLeft: "auto",
        marginRight: "auto",
        marginTop: "5%",
        marginBottom: "3%",
        padding: "2.5%",
        borderRadius: 10,
        borderColor: "gray",
    },
    buttonViewLoggedIn: {
        width: 200,
        borderWidth: 0.5,
        marginLeft: "auto",
        marginRight: "auto",
        marginTop: "5%",
        marginBottom: "3%",
        padding: "2.5%",
        borderRadius: 10,
        borderColor: "blue",
    },
});

export default HomeScreen;
